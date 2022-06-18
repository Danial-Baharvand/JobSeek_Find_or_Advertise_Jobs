package classes;

import com.google.common.collect.Sets;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static classes.Config.*;

public class IO {

    public IO() {

    }

    public static void newLine(String path, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJobseekers() {
    }

    public static void writeJobs() {

    }

    public static void writeCategories() {

    }

    public static void writeRecruiters() {
    }

    public void clearFileContent(String path) {
        try {
            PrintWriter pw = new PrintWriter(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addToDB(Object data) {
        if (data instanceof JobSeeker) {
            newLine(DT_JOBSEEKERS, ((JobSeeker) data).toWriteFormat());
        } else if (data instanceof Recruiter) {
            newLine(DT_RECRUITERS, ((Recruiter) data).toWriteFormat());
        } else if (data instanceof Admin) {
            newLine(DT_ADMINS, data.toString());
        } else if (data instanceof Job) {
            newLine(DT_JOBS, ((Job) data).toWriteFormat());
        }else if (data instanceof Category) {
            newLine(DT_CATEGORIES, data.toString());
        } else {
            System.out.println("Type not compatible to write to database");
        }
    }
    public static void updateDB(HashMap<String, Object> data){

    }
    public static void updateDB(Object data){

    }
    public void writeMapEntry(String key, String value, String path){
        newLine(path, key + "=" + value);
    }

    public HashMap<String, JobSeeker> readJobSeekers() {
        String line;
        HashMap<String, JobSeeker> jobSeekers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Config.DT_JOBSEEKERS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(SEPARATOR_1);
                JobSeeker jobSeeker = new JobSeeker(userData[EMAIL], userData[NAME], userData[PASSWORD]);
                jobSeeker.setActive(Boolean.parseBoolean(userData[IS_ACTIVE]));
                jobSeeker.setResumeFile(userData[RESUME]);
                jobSeeker.getSkills().addAll(Arrays.asList(userData[SKILLS].split(SEPARATOR_2)));
                jobSeekers.putIfAbsent(userData[EMAIL], jobSeeker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobSeekers;
    }

    public HashMap<String, Recruiter> readRecruiters() {
        String line;
        HashMap<String, Recruiter> recruiters = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_RECRUITERS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(SEPARATOR_1);
                Recruiter recruiter = new Recruiter(userData[EMAIL], userData[NAME], userData[PASSWORD],
                        userData[ORGANISATION]);
                recruiter.setActive(Boolean.parseBoolean(userData[IS_ACTIVE]));
                recruiters.putIfAbsent(userData[EMAIL], recruiter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recruiters;
    }

    public HashMap<String, Admin> readAdmins() {
        String line;
        HashMap<String, Admin> admins = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_ADMINS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(",");
                Admin admin = new Admin(userData[EMAIL], userData[NAME], userData[PASSWORD]);
                admins.putIfAbsent(userData[EMAIL], admin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public HashMap<String, Job> readJobs(HashMap<String, Recruiter> recruiters, HashMap<String, JobSeeker> jobSeekers) {
        String line;
        HashMap<String, Job> jobs = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_JOBS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(SEPARATOR_1);
                Job job = new Job();
                job.jobTitle = userData[JOB_TITLE];
                job.states =Set.of(userData[JOB_STATES].split(SEPARATOR_2));
                job.salary =Integer.parseInt(userData[JOB_SALARY]);
                job.jobType =userData[JOB_TYPE];
                job.keywords =userData[JOB_KEYWORDS];
                job.jobDescription =userData[JOB_DESCRIPTION].replace(SEPARATOR_2, "\n");
                job.recruiter = recruiters.get(userData[JOB_RECRUITER]);
                job.recruiter.addJob(job);
                job.published =Boolean.parseBoolean(userData[JOB_PUBLISHED]);
                readApplications(job, userData[APPLICATIONS], jobSeekers);
                readInvitations(job, userData[INVITATIONS], jobSeekers);
                job.setJobID();
                jobs.putIfAbsent(job.getID(), job);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobs;
    }
    private void readApplications(Job job, String data, HashMap<String, JobSeeker> jobSeekers){
        String[] appsData = data.split(SEPARATOR_2);
        for (String appData:appsData) {
            String[] fields = appData.split(SEPARATOR_3);
            JobSeeker jobSeeker = jobSeekers.get(fields[MAIL_JOBSEEKER]);
            Application application = new Application(jobSeeker,job );
            job.applications().add(application);
            jobSeeker.applications().add(application);
        }
    }
    private void readInvitations(Job job, String data, HashMap<String, JobSeeker> jobSeekers){
        String[] invsData = data.split(SEPARATOR_2);
        for (String invData:invsData) {
            String[] fields = invData.split(SEPARATOR_3);
            JobSeeker jobSeeker = jobSeekers.get(fields[MAIL_JOBSEEKER]);
            String message = fields[MAIL_MESSAGE].replace(SEPARATOR_4, "\n");
            Invitation invitation = new Invitation(jobSeeker,job, message );
            job.invitations().add(invitation);
            jobSeeker.invitations().add(invitation);
        }
    }

    public HashMap<String, String> readMessages() {
        String line;
        HashMap<String, String> messages = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_MESSAGES))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split("=");
                messages.putIfAbsent(userData[0], userData[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public Set<String> readInactiveUsers() {
        String line;
        Set<String> emails = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_INACTIVE_USERS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                emails.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emails;

    }

    public CategoryManager readCategories() {
        return null;
    }
}

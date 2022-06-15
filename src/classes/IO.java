package classes;

import com.google.common.collect.Sets;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static classes.Config.*;

public class IO {

    public IO() {

    }

    public void newLine(String path, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFileContent(String path) {
        try {
            PrintWriter pw = new PrintWriter(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeToDB(Object data) {
        if (data instanceof JobSeeker) {
            newLine(DT_JOBSEEKERS, data.toString());
        } else if (data instanceof Recruiter) {
            newLine(DT_RECRUITERS, data.toString());
        } else if (data instanceof Admin) {
            newLine(DT_RECRUITERS, data.toString());
        } else if (data instanceof Job) {
            newLine(DT_JOBS, data.toString());
        } else if (data instanceof String) {
            newLine(DT_MESSAGES, data.toString());
        }
    }

    public HashMap<String, JobSeeker> readJobSeekers(BiMultiMap<String> skills) {
        String line;
        HashMap<String, JobSeeker> jobSeekers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Config.DT_JOBSEEKERS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(",");
                JobSeeker jobSeeker = new JobSeeker(userData[EMAIL], userData[NAME], userData[PASSWORD]);
                jobSeeker.getSkills().addAll(skills.get(userData[EMAIL]));
                jobSeeker.setSkills(Sets.newHashSet(skills.get(jobSeeker.getEmail())));
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
                String[] userData = line.split(",");
                Recruiter recruiter = new Recruiter(userData[EMAIL], userData[NAME], userData[PASSWORD],
                        userData[ORGANISATION]);
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

    public HashMap<String, Job> readJobs(HashMap<String, Recruiter> recruiters) {
        String line;
        HashMap<String, Job> jobs = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_JOBS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split("\\|");
                Job job = new Job(line, recruiters);
                jobs.putIfAbsent(userData[JOB_TITLE] + userData[JOB_RECRUITER], job);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobs;
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
}

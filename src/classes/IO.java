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

    /**
     * Remove contents of a file
     * @param path of the file
     */
    public static void clearFileContent(String path) {
        try {
            PrintWriter pw = new PrintWriter(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the data to path and goes to the next line
     * @param path of the file
     * @param data data to be written
     */
    public static void newLine(String path, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write all jobseekers in memory to file
     */
    public static void writeJobseekers() {
        clearFileContent(DT_JOBSEEKERS);
        Runtime.accountManager().getJobSeekers().forEach((k, j) -> IO.newLine(DT_JOBSEEKERS, j.toWriteFormat()));
    }

    /**
     * Write all jobs in memory to file
     */
    public static void writeJobs() {
        clearFileContent(DT_JOBS);
        Runtime.accountManager().getJobs().forEach((k, j) -> IO.newLine(DT_JOBS, j.toWriteFormat()));
    }

    /**
     * Write all categories in memory to file
     */
    public static void writeCategories() {
        clearFileContent(DT_CATEGORIES);
        newLine(DT_CATEGORIES, Runtime.accountManager().getCategories().toWriteFormat());
    }

    /**
     * Write all recruiters in memory to file
     */
    public static void writeRecruiters() {
        clearFileContent(DT_RECRUITERS);
        Runtime.accountManager().getRecruiters().forEach((k, r) -> IO.newLine(DT_RECRUITERS, r.toWriteFormat()));

    }


    /**
     * Adds the object to file depending on the object type
     * Makes writing to file easier and cleaner
     * originally was meant to be more heavily used
     * @param data to be written to file
     */
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

    /**
     * Read jobseekers from file
     * @return jobseekers
     */
    public HashMap<String, JobSeeker> readJobSeekers() {
        String line;
        HashMap<String, JobSeeker> jobSeekers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Config.DT_JOBSEEKERS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) { // there are more lines
                String[] userData = line.split(SEPARATOR_1, -1);
                JobSeeker jobSeeker = new JobSeeker(userData[EMAIL], userData[NAME], userData[PASSWORD]);
                jobSeeker.setActive(Boolean.parseBoolean(userData[IS_ACTIVE]));
                jobSeeker.setResumeFile(userData[RESUME]);
                Arrays.stream(userData[SKILLS].split(SEPARATOR_2)).filter(a -> !a.equals("")).forEach(jobSeeker::addSkill);
                jobSeekers.putIfAbsent(userData[EMAIL], jobSeeker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobSeekers;
    }

    /**
     * Read recruiters from file
     * @return recruiters
     */
    public HashMap<String, Recruiter> readRecruiters() {
        String line;
        HashMap<String, Recruiter> recruiters = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_RECRUITERS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(SEPARATOR_1, -1);
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

    /**
     * Read admins from file
     * @return admins
     */
    public HashMap<String, Admin> readAdmins() {
        String line;
        HashMap<String, Admin> admins = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_ADMINS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(",", -1);
                Admin admin = new Admin(userData[EMAIL], userData[NAME], userData[PASSWORD]);
                admins.putIfAbsent(userData[EMAIL], admin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admins;
    }

    /**
     * read jobs from file
     * @param recruiters all recruiters
     * @param jobSeekers all jobseekers
     * @param mainCategories universal categories set by admin
     * @return jobs
     */
    public HashMap<String, Job> readJobs(HashMap<String, Recruiter> recruiters, HashMap<String, JobSeeker> jobSeekers, CategoryManager mainCategories) {
        String line;
        HashMap<String, Job> jobs = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_JOBS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(SEPARATOR_1, -1);
                Job job = new Job();
                job.setJobTitle(userData[JOB_TITLE]);
                job.setStates(Set.of(userData[JOB_STATES].split(SEPARATOR_2)));
                job.setSalary(Integer.parseInt(userData[JOB_SALARY]));
                job.setJobType(userData[JOB_TYPE]);
                job.setKeywords(userData[JOB_KEYWORDS]);
                job.setJobDescription(userData[JOB_DESCRIPTION].replace(SEPARATOR_2, "\n"));
                job.setRecruiter(recruiters.get(userData[JOB_RECRUITER]));
                job.getRecruiter().addJob(job);
                job.setPublished(Boolean.parseBoolean(userData[JOB_PUBLISHED]));
                readApplications(job, userData[APPLICATIONS], jobSeekers);
                readInvitations(job, userData[INVITATIONS], jobSeekers);
                job.setCategories(readJobCategories(mainCategories, userData[JOB_CATEGORIES]));
                job.setJobID();
                jobs.putIfAbsent(job.getID(), job);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    /**
     * Read job categories from file. These categories are specific to each job.
     * @param mainCategories universal categories
     * @param data job categories in file format
     * @return job categories
     */
    private CategoryManager readJobCategories(CategoryManager mainCategories, String data){
        CategoryManager jobCats = new CategoryManager();
        String[] catNames = data.split(SEPARATOR_2);
        for (String catName:catNames) {
            jobCats.add(mainCategories.getByName(catName));
        }
        return jobCats;
    }

    /**
     * Read applications from file
     * @param job for the application
     * @param data is the application information in the file format
     * @param jobSeekers all jobseekers
     */
    private void readApplications(Job job, String data, HashMap<String, JobSeeker> jobSeekers){
        String[] appsData = data.split(SEPARATOR_2);
        for (String appData:appsData) {
            if (!appData.equals("")){
                String[] fields = appData.split(SEPARATOR_3);
                JobSeeker jobSeeker = jobSeekers.get(fields[MAIL_JOBSEEKER]);
                Application application = new Application(jobSeeker,job );
                job.applications().add(application);
                jobSeeker.applications().add(application);
            }
        }
    }

    /**
     * Read invitations from file
     * @param job for the invitation
     * @param data of the invitation in the file format
     * @param jobSeekers all jobseekers
     */
    private void readInvitations(Job job, String data, HashMap<String, JobSeeker> jobSeekers){
        String[] invsData = data.split(SEPARATOR_2);
        for (String invData:invsData) {
            if (!invData.equals("")) {
                String[] fields = invData.split(SEPARATOR_3);
                JobSeeker jobSeeker = jobSeekers.get(fields[MAIL_JOBSEEKER]);
                String message = fields[MAIL_MESSAGE].replace(SEPARATOR_4, "\n");
                Invitation invitation = new Invitation(jobSeeker, job, message);
                job.invitations().add(invitation);
                jobSeeker.invitations().add(invitation);
            }
        }
    }

    /**
     * Read the main categories from file
     * @return main categories
     */
    public CategoryManager readCategories() {
        String line;
        CategoryManager categories = new CategoryManager();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_CATEGORIES))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] catData = line.split(SEPARATOR_3);
                Category category = new Category(catData[CATEGORY_NAME]);
                if (catData.length>1){
                    category.addAll(Arrays.asList(catData[CATEGORY_KEYWORDS].split(SEPARATOR_4)));
                }
                categories.add(category);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categories;
    }
}

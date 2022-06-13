package classes;

import java.util.HashMap;

public class AccountManagement {
    private HashMap<String, JobSeeker> jobSeekers;
    private HashMap<String, Recruiter> recruiters;
    private HashMap<String, Admin> admins;
    private HashMap<String, Job> jobs;
    private BiMultiMap<String> categories = new BiMultiMap();
    private BiMultiMap<String> skills = new BiMultiMap();
    private BiMultiMap<String> recruiterJobs = new BiMultiMap();
    private BiMultiMap<String> jobApplications = new BiMultiMap();
    private User currentUser;



    public AccountManagement() {
        this.readDatabase();
    }
    private void readDatabase() {
        IO io = new IO();
        skills.readFromFile(Config.DT_SKILLS);
        categories.readFromFile(Config.DT_CATEGORIES);
        jobSeekers = io.readJobSeekers(skills);
        recruiters = io.readRecruiters();
        admins = io.readAdmins();
        jobs = io.readJobs(recruiters);

        recruiterJobs.readFromFile(Config.DT_RECRUITER_JOBS);
        jobApplications.readFromFile(Config.DT_JOB_APPLICATIONS);
    }
    public void addUser(User user){
        if (user instanceof JobSeeker){
            jobSeekers.put(user.getEmail(), (JobSeeker) user);
        }else if (user instanceof Recruiter) {
            recruiters.put(user.getEmail(), (Recruiter) user);
        }else if (user instanceof Admin) {
            admins.put(user.getEmail(), (Admin) user);
        }
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * updates the registeredUsers Hashmap by calling the readRegisteredUsers method to read the registeredUsers file
     * @param email is the email to search for in the registeredUsers Hashmap
     * @return true if the email already exists as a Key in the registeredUsers Hashmap, else false
     */
    public boolean isRegisteredUser(String email) {
        return jobSeekers.containsKey(email) || recruiters.containsKey(email) || admins.containsKey(email);
    }

    public HashMap<String, JobSeeker> getJobSeekers() {
        return jobSeekers;
    }

    public HashMap<String, Recruiter> getRecruiters() {
        return recruiters;
    }

    public HashMap<String, Admin> getAdmins() {
        return admins;
    }

    public BiMultiMap getCategories() {
        return categories;
    }

    public BiMultiMap getSkills() {
        return skills;
    }

    public BiMultiMap<String> getRecruiterJobs() {
        return recruiterJobs;
    }

    public BiMultiMap<String> getJobApplications() {
        return jobApplications;
    }

    public HashMap<String, Job> getJobs() {
        return jobs;
    }
}
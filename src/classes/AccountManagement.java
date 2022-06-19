package classes;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AccountManagement {
    private HashMap<String, JobSeeker> jobSeekers;
    private HashMap<String, Recruiter> recruiters;
    private HashMap<String, Admin> admins;
    private HashMap<String, Job> jobs;
    private CategoryManager categories = new CategoryManager();
    private User currentUser;



    public AccountManagement() {
        this.readDatabase();
    }

    /**
     * Reads the database
     */
    private void readDatabase() {
        IO io = new IO();
        categories= io.readCategories();
        jobSeekers = io.readJobSeekers();
        recruiters = io.readRecruiters();
        admins = io.readAdmins();
        jobs = io.readJobs(recruiters, jobSeekers, categories);
    }

    /**
     * Adds a user to the appropriate variable depending on its type
     * @param user
     */
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
     * Checks if the user email is in the database
     * @param email to check
     * @return true of user is registered
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

    public CategoryManager getCategories() {
        return categories;
    }
    public HashMap<String, Job> getJobs() {
        return jobs;
    }


}
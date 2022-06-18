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
    private void readDatabase() {
        IO io = new IO();
        //categories= io.readCategories();
        jobSeekers = io.readJobSeekers();
        recruiters = io.readRecruiters();
        admins = io.readAdmins();
        jobs = io.readJobs(recruiters, jobSeekers);
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

    public CategoryManager getCategories() {
        return categories;
    }
    public HashMap<String, Job> getJobs() {
        return jobs;
    }


}
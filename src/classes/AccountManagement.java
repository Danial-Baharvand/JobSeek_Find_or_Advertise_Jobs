package classes;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.HashMap;
public class AccountManagement {
    private HashMap<String, JobSeeker> jobseekers = new HashMap<>();
    private HashMap<String, Recruiter> recruiters = new HashMap<>();
    private HashMap<String, Admin> admins = new HashMap<>();
    private BiMultiMap categories = new BiMultiMap();
    private BiMultiMap Skills = new BiMultiMap();
    private Jobs jobs = new Jobs();
    private User currentUser;



    public AccountManagement() {
        this.readUsers();
    }
    private void readUsers() {
        IO io = new IO();
        jobseekers = io.readJobSeekers();
        recruiters = io.readRecruiters();
        admins = io.readAdmins();
    }
    public void addUser(User user){
        if (user instanceof JobSeeker){
            jobseekers.put(user.getEmail(), (JobSeeker) user);
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
        return jobseekers.containsKey(email) || recruiters.containsKey(email) || admins.containsKey(email);
    }

    public HashMap<String, JobSeeker> getJobseekers() {
        return jobseekers;
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
        return Skills;
    }

    public Jobs getJobs() {
        return jobs;
    }
}
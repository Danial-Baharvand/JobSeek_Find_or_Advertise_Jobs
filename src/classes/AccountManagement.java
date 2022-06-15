package classes;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AccountManagement {
    private HashMap<String, JobSeeker> jobSeekers;
    private HashMap<String, Recruiter> recruiters;
    private HashMap<String, Admin> admins;
    private Set<String> inactiveUsers;
    private HashMap<String, Job> jobs;
    private HashMap<String, String> messages;
    private final BiMultiMap<String> categories = new BiMultiMap<>();
    private final BiMultiMap<String> skills = new BiMultiMap<>();
    private final JobMap recruiterJobs = new JobMap();
    private final JobMap jobApplications = new JobMap();
    private final JobMap jobInvitations = new JobMap();
    private final JobMap jobCategories = new JobMap();
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
        messages = io.readMessages();
        recruiterJobs.readFromFile(Config.DT_RECRUITER_JOBS, jobs);
        jobApplications.readFromFile(Config.DT_JOB_APPLICATIONS, jobs);
        jobInvitations.readFromFile(Config.DT_JOB_INVITATIONS, jobs);
        jobCategories.readFromFile(Config.DT_JOB_CATEGORIES, jobs);
        inactiveUsers = io.readInactiveUsers();
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

    public BiMultiMap<String> getCategories() {
        return categories;
    }

    public BiMultiMap<String> getSkills() {
        return skills;
    }

    public JobMap getRecruiterJobs() {
        return recruiterJobs;
    }

    public JobMap getJobApplications() {
        return jobApplications;
    }

    public HashMap<String, Job> getJobs() {
        return jobs;
    }

    public JobMap getJobInvitations() {
        return jobInvitations;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public JobMap getJobCategories() {
        return jobCategories;
    }

    public Set<String> getInactiveUsers(){
        return inactiveUsers;
    }
}
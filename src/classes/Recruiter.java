package classes;

import java.util.HashSet;
import java.util.Set;

import static classes.Config.SEPARATOR_1;
import static classes.Config.SEPARATOR_2;

public class Recruiter extends User {
    String org;
    Set<Job> jobs = new HashSet<>();


    public Recruiter() {
        super("", "", "");
        org = "";
    }

    public Recruiter(String email, String fullName, String password, String org) {
        super(email, fullName, password);
        this.org = org;

    }

    /**
     * @return recruiter in the correct format to be written to a file
     */
    public String toWriteFormat() {
        return   getEmail() + SEPARATOR_1 +
                getFullName() + SEPARATOR_1 +
                getPassword() + SEPARATOR_1 +
                isActive() + SEPARATOR_1 +
                getOrg() + SEPARATOR_1 +
                printJobs();
    }

    public String getOrg() {
        return org;
    }

    /**
     * @return jobs created by the recruiter in the current format to be written to a file
     */
    private String printJobs(){
        StringBuilder s = new StringBuilder();
        String separator = "";
        for (Job job:jobs) {
            s.append(separator).append(job);
            separator = SEPARATOR_2;
        }
        return s.toString();
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
    public boolean hasJob(String title){
        return jobs.stream().anyMatch(j -> j.getJobTitle().equals(title));
    }
    public void addJob(Job job){
        jobs.add(job);
    }
    public void removeJob(Job job){
        jobs.remove(job);
    }
}

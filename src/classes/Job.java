package classes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Job {
    Recruiter recruiter;
    String jobTitle;
    String state;
    int salary;
    String jobType;
    String keywords;
    String jobDescription;
    Boolean published = false;

    public Job(){
        this.jobTitle = "This job was removed";
    }
    public Job(String jobDetails, HashMap<String, Recruiter> recruiters){
        //this.categories = Runtime.accountManager().getJobCategories().
        String[] setValues = jobDetails.split("\\|");
        this.jobTitle = setValues[0];
        this.state =setValues[1];
        this.salary =Integer.parseInt(setValues[2]);
        this.jobType =setValues[3];
        this.keywords =setValues[4];
        this.jobDescription =setValues[5].replace("$$newline$$", "\n");
        this.recruiter = recruiters.get(setValues[6]);
        this.published =Boolean.parseBoolean(setValues[7]);
    }
    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return   jobTitle + '|' +
                 state + '|' +
                 salary + '|' +
                 jobType + '|' +
                 keywords + '|' +
                 jobDescription.replace("\n", "$$newline$$")
                         .replace("\r", "$$newline$$") + '|' +
                 recruiter.getEmail() + '|' +
                 published;
    }
    public String getID(){
        return jobTitle + recruiter.getEmail();
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getState() {
        return state;
    }

    public int getSalary() {
        return salary;
    }

    public String getJobType() {
        return jobType;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}

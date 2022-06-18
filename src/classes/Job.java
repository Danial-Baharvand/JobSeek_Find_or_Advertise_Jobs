package classes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static classes.Config.*;

public class Job implements Inbox{
    String jobID;
    Recruiter recruiter;
    String jobTitle;
    Set<String> states;
    int salary;
    String jobType;
    String keywords;
    String jobDescription;
    Boolean published = false;
    CategoryManager categories;



    public Job(){
        this.jobTitle = "This job was removed";
    }
    public Job(String jobDetails, HashMap<String, Recruiter> recruiters){
        categories = new CategoryManager();
        String[] setValues = jobDetails.split(SEPARATOR_1);
        this.jobTitle = setValues[JOB_TITLE];
        this.states =Set.of(setValues[JOB_STATES].split(SEPARATOR_2));
        this.salary =Integer.parseInt(setValues[JOB_SALARY]);
        this.jobType =setValues[JOB_TYPE];
        this.keywords =setValues[JOB_KEYWORDS];
        this.jobDescription =setValues[JOB_DESCRIPTION].replace(SEPARATOR_2, "\n");
        this.recruiter = recruiters.get(setValues[JOB_RECRUITER]);
        this.published =Boolean.parseBoolean(setValues[JOB_PUBLISHED]);
        this.jobID = this.jobTitle + this.recruiter;
    }
    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }


    public String toWriteFormat() {
        return   jobTitle + SEPARATOR_1 +
                 String.join(SEPARATOR_2, states) + SEPARATOR_1 +
                 salary + SEPARATOR_1 +
                 jobType + SEPARATOR_1 +
                 keywords + SEPARATOR_1 +
                 jobDescription.replace("\n", SEPARATOR_2)
                         .replace("\r", SEPARATOR_2) + SEPARATOR_1 +
                 applications() + SEPARATOR_1 +
                 invitations() + SEPARATOR_1 +
                 recruiter + SEPARATOR_1 +
                 published+ SEPARATOR_1 +
                 categories;
    }
    @Override
    public String toString() {
        return   this.jobID;
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

    public Set<String> getStates() {
        return states;
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

    public void setStates(Set<String> states) {
        this.states = states;
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
    public CategoryManager categories() {
        return categories;
    }
    public void setJobID() {
        this.jobID = this.jobTitle + this.recruiter;
    }

    public void setCategories(CategoryManager categories) {
        this.categories = categories;
    }
}

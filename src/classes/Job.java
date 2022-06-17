package classes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
        String[] setValues = jobDetails.split("\\|");
        this.jobTitle = setValues[0];
        this.states =Set.of(setValues[1].split("%%"));
        this.salary =Integer.parseInt(setValues[2]);
        this.jobType =setValues[3];
        this.keywords =setValues[4];
        this.jobDescription =setValues[5].replace("$$newline$$", "\n");
        this.recruiter = recruiters.get(setValues[6]);
        this.published =Boolean.parseBoolean(setValues[7]);
        this.jobID = this.jobTitle + this.recruiter;
    }
    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }


    public String toString2() {
        return   jobTitle + '|' +
                 String.join("%%", states) + '|' +
                 salary + '|' +
                 jobType + '|' +
                 keywords + '|' +
                 jobDescription.replace("\n", "$$newline$$")
                         .replace("\r", "$$newline$$") + '|' +
                 recruiter.getEmail() + '|' +
                 published;
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

    public void addCategory(Category category) {
        this.categories.add(category);
    }


}

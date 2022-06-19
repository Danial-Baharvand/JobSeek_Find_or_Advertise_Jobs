package classes;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static classes.Config.*;

public class Job implements Inbox{
    private String jobID;
    private Recruiter recruiter;
    private String jobTitle;
    private Set<String> states;
    private int salary;
    private String jobType;
    private String keywords;
    private String jobDescription;
    private Boolean published = false;
    private CategoryManager categories;
    private final Applications applications = new Applications();
    private final Invitations invitations = new Invitations();


    public Job(){
        // if job is deleted or can't be found
        this.jobTitle = "This job was removed";
    }
    public Boolean isPublished() {
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

    /**
     * returns the relevant terms for the job
     * @return
     */
    public Set<String> searchTerms(){
        Set<String> terms = categories.stream().flatMap(Set::stream).collect(Collectors.toSet());
        Collections.addAll(terms, jobTitle.split(" "));
        Collections.addAll(terms, keywords.split(" "));
        Collections.addAll(terms, jobDescription.split(" "));
        terms.add(recruiter.getOrg());

        return terms;
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
        //generate ID
        this.recruiter = recruiter;
        this.jobID = this.jobTitle + this.recruiter;
    }

    public void setJobTitle(String jobTitle) {
        // generate ID
        this.jobTitle = jobTitle;
        this.jobID = this.jobTitle + this.recruiter;
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

    @Override
    public Applications applications() {
        return applications;
    }

    @Override
    public Invitations invitations() {
        return invitations;
    }
}

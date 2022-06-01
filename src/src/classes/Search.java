package classes;

import java.util.ArrayList;

public class Search {
    String searchText;
    String state;
    String cat;
    int salary;
    String jobType;
    String keywords;
    ArrayList<Job> jobs = new ArrayList<Job>();
    public Search(){}
    public Search(String searchText, String state, String cat, int salary, String jobType, String keywords){
        this.searchText = searchText;
        this.state = state;
        this.cat = cat;
        this. salary = salary;
        this.jobType = jobType;
        this.keywords = keywords;
    }

    public String getSearchText() {
        return searchText;
    }

    public String getState() {
        return state;
    }

    public String getCat() {
        return cat;
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

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCat(String cat) {
        this.cat = cat;
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

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }
    public void addJob(Job job) {
        this.jobs.add(job);
    }
}

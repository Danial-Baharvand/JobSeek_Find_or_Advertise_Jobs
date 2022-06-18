package classes;

import java.util.*;

public class Search {
    String searchText;
    Set<String> states;
    Set<String> cats;
    Set<String> jobTypes;
    int salary;
    Collection<Job> jobs;


    public Search(Collection<Job> jobs){
        this.jobs = jobs;
        //setScores(jobs);
    }

    /**
     * Uses the scorer object to score all jobs in the Jobs set.
     */
    public Map<Integer, Job> scoreJobs(){
        Scorer scorer = new Scorer();
        Map<Integer, Job> scoredJobs = new TreeMap<>(Collections.reverseOrder());
        jobs.forEach(j -> scoredJobs.put(scorer.scoreAgainstSearch(this, j), j));
        return scoredJobs;

    }
    public String getSearchText() {
        return searchText;
    }




    public int getSalary() {
        return salary;
    }

    public Collection<Job> getJobs() {
        return jobs;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }


    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setJobs(HashSet<Job> jobs) {
        this.jobs = jobs;
    }
    public void addJob(Job job) {
        this.jobs.add(job);
    }

    public Set<String> getStates() {
        return states;
    }

    public void setStates(Set<String> states) {
        this.states = states;
    }

    public Set<String> getCats() {
        return cats;
    }

    public void setCats(Set<String> cats) {
        this.cats = cats;
    }

    public Set<String> getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(Set<String> jobTypes) {
        this.jobTypes = jobTypes;
    }
}

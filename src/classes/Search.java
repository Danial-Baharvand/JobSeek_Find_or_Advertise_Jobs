package classes;

import java.util.*;

public class Search {
    String searchText;
    ArrayList<String> states;
    ArrayList<String> cats;
    ArrayList<String> jobTypes;
    int salary;
    String keywords;
    Collection<Job> jobs;
    ArrayList<ScoredJob> scoredJobs = new ArrayList<>();

    public Search(Collection<Job> jobs){
        this.jobs = jobs;
        //setScores(jobs);
    }

    /**
     * Uses the scorer object to score all jobs in the Jobs set.
     */
    public void setScores(){
        Scorer scorer = new Scorer();
        for (Job job: jobs){
            int skillScore = 100; //to be implemented
            int searchScore = skillScore;
            if (getSearchText() != null){
                searchScore = scorer.scoreAgainstSearch(this, job);
            }

            scoredJobs.add(new ScoredJob(job, searchScore/2 + skillScore/2));
        }
        scoredJobs.sort(Comparator.comparing(ScoredJob::getScore).reversed());
    }
    public String getSearchText() {
        return searchText;
    }




    public int getSalary() {
        return salary;
    }


    public String getKeywords() {
        return keywords;
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


    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setJobs(HashSet<Job> jobs) {
        this.jobs = jobs;
    }
    public void addJob(Job job) {
        this.jobs.add(job);
    }

    public ArrayList<ScoredJob> getScoredJobs() {
        return scoredJobs;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public void setStates(ArrayList<String> states) {
        this.states = states;
    }

    public ArrayList<String> getCats() {
        return cats;
    }

    public void setCats(ArrayList<String> cats) {
        this.cats = cats;
    }

    public ArrayList<String> getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(ArrayList<String> jobTypes) {
        this.jobTypes = jobTypes;
    }
}

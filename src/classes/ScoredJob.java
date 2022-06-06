package classes;

import java.util.Comparator;

public class ScoredJob {
    Job job;
    int score;
    public ScoredJob(Job job, int score){
        this.job = job;
        this. score = score;
    }

    public Job getJob() {
        return job;
    }

    public int getScore() {
        return score;
    }
}

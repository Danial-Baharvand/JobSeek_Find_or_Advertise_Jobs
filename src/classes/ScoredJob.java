package classes;

/**
 * A class that stores a job and a scored assigned to it based on how relevant it is to a certain criteria
 */
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

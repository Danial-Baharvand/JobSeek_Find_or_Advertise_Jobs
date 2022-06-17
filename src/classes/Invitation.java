package classes;

import static classes.Config.SEPARATOR_3;

public class Invitation extends Mail {
    String message;
    public Invitation(JobSeeker jobSeeker, Job job, String message) {
        super(jobSeeker, job);
        this.message = message;
    }
    @Override
    public String toString(){
        return getJobSeeker()+ SEPARATOR_3 + getJob()+ SEPARATOR_3 + message;
    }
}

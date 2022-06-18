package classes;


import static classes.Config.SEPARATOR_3;

public class Application extends Mail {
    public Application(JobSeeker jobSeeker, Job job) {
        super(jobSeeker, job);
    }
    @Override
    public String toString(){
        return getJobSeeker()+ SEPARATOR_3 + getJob();
    }
}

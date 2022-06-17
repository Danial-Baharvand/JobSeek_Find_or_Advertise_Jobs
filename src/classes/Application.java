package classes;

public class Application extends Mail {
    public Application(JobSeeker jobSeeker, Job job) {
        super(jobSeeker, job);
    }
    @Override
    public String toString(){
        return getJobSeeker()+ "|>3<|" + getJob();
    }
}

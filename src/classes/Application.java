package classes;


import static classes.Config.SEPARATOR_3;

/**
 * Holds an application
 */
public class Application extends Mail {
    public Application(JobSeeker jobSeeker, Job job) {
        super(jobSeeker, job);
    }

    /**
     *
     * @return the object in proper format to be written to database
     */
    @Override
    public String toString(){
        return getJobSeeker()+ SEPARATOR_3 + getJob();
    }
}

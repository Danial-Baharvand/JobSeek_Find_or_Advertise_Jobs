package classes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Holds a set of applications
 */
public class Applications extends MailManager<Application> {
    /**
     * Remove an application given its job
     * @param job for which the appliction is deleted
     */
    public void removeByJob(Job job){
        this.remove(this.stream().filter(a -> a.getJob().equals(job)).findFirst());
    }

    /**
     * Remove the aplication given its jobseeker
     * @param jobSeeker
     */
    public void removeByJobSeeker(JobSeeker jobSeeker){
        this.remove(this.stream().filter(a -> a.getJobSeeker().equals(jobSeeker)).findFirst());
    }
}

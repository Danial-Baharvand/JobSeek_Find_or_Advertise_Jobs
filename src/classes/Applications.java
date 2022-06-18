package classes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Applications extends MailManager<Application> {
/*    Set<Application> applications = new HashSet<>();
    public void add(Application application){
        applications.add(application);
    }
    public void remove(Application application){
        applications.remove(application);
    }
    public Collection<Job> getJobs(){
        return applications.stream().map(Mail::getJob).collect(Collectors.toSet());
    }
    public Collection<JobSeeker> getJobSeekers(){
        return applications.stream().map(Mail::getJobSeeker).collect(Collectors.toSet());
    }*/
    public void removeByJob(Job job){
        this.remove(this.stream().filter(a -> a.getJob().equals(job)).findFirst());
    }
    public void removeByJobSeeker(JobSeeker jobSeeker){
        this.remove(this.stream().filter(a -> a.getJobSeeker().equals(jobSeeker)).findFirst());
    }
    public void removeJobSeekerApplications(Job job){
        this.stream().forEach(a -> a.getJobSeeker());
    }
}

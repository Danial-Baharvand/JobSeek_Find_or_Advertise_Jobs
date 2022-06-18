package classes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static classes.Config.SEPARATOR_2;

public abstract class MailManager<T extends Mail> extends HashSet<T> {
    public Collection<Job> getJobs(){
        return this.stream().map(T::getJob).collect(Collectors.toSet());
    }
    public Collection<JobSeeker> getJobSeekers(){
        return this.stream().map(T::getJobSeeker).collect(Collectors.toSet());
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        String separator = "";
        for (T mail:this) {
            s.append(separator).append(mail);
            separator = SEPARATOR_2;
        }
        return s.toString();
    }

}

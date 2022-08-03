package classes;

import static classes.Config.*;

/**
 * An invitation is a mail with a message.
 * Objects are created by the recruiters and hold reference to the jobseekser and the job
 */
public class Invitation extends Mail {
    String message;
    public Invitation(JobSeeker jobSeeker, Job job, String message) {
        super(jobSeeker, job);
        this.message = message;
    }

    /**
     * @return the writable representation of an invitation
     */
    @Override
    public String toString(){
        return getJobSeeker()+ SEPARATOR_3 + getJob()+ SEPARATOR_3 + message.replace("\n", SEPARATOR_4)
                .replace("\r", SEPARATOR_4);
    }
}

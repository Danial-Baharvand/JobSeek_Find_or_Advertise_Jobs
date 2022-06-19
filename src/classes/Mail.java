package classes;

/**
 * Represents of a "mail" between a jobseeker and a job
 */
abstract class Mail {
    private JobSeeker jobSeeker;
    private Job job;
    public Mail(JobSeeker jobSeeker, Job job){
        this.jobSeeker = jobSeeker;
        this.job = job;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}

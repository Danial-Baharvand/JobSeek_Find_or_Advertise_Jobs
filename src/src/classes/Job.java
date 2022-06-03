package classes;

public class Job {
    int JobID;
    Recruiter recruiter;
    String jobTitle;
    String state;
    String cat;
    int salary;
    String jobType;
    String keywords;

    public int getJobID() {
        return JobID;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getState() {
        return state;
    }

    public String getCat() {
        return cat;
    }

    public int getSalary() {
        return salary;
    }

    public String getJobType() {
        return jobType;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setJobID(int jobID) {
        JobID = jobID;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}

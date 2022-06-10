package classes;

public class Job {
    Recruiter recruiter;

    String jobTitle;
    String state;
    String cat;
    int salary;
    String jobType;
    String keywords;
    String jobDescription;
    Boolean published = false;
    public Job(){}
    public Job(String jobDetails){
        String[] setValues = jobDetails.split("|");
        ;
        this.jobTitle = setValues[0];
        this.state =setValues[1];
        this.cat =setValues[2];
        this.salary =Integer.parseInt(setValues[3]);
        this.jobType =setValues[4];
        this.keywords =setValues[5];
        this.jobDescription =setValues[6];
        this.recruiter = Runtime.accountManager().getRecruiters().get(setValues[7]);
        this.published =Boolean.parseBoolean(setValues[8]);
    }
    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return  recruiter.getEmail() +
                "=" + jobTitle + '|' +
                 state + '|' +
                 cat + '|' +
                 salary + '|' +
                 jobType + '|' +
                 keywords + '|' +
                 jobDescription + '|' +
                 recruiter.getEmail() + '|' +
                 published;
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

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}

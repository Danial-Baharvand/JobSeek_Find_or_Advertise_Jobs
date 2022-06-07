package classes;

public class Recruiter extends User {
    String org;
    String recruiterDescription;


    public Recruiter() {
        super("", "", "");
        org = "";
        recruiterDescription = "";
    }

    public Recruiter(String email, String fullName, String password, String org, String recruiterDescription) {
        super(email, fullName, password);
        this.org = org;
        this.recruiterDescription = recruiterDescription;

    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getRecruiterDescription() {
        return recruiterDescription;
    }

    public void setRecruiterDescription(String recruiterDescription) {
        this.recruiterDescription = recruiterDescription;
    }
}

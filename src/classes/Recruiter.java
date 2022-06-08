package classes;

public class Recruiter extends User {
    String org;
    String recruiterDescription;


    public Recruiter() {
        super("", "", "");
        org = "";
        recruiterDescription = "";
    }

    public Recruiter(String email, String fullName, String password, String org) {
        super(email, fullName, password);
        this.org = org;

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
    public String toString() {
        return String.format("%s,%s,%s,%s", getEmail(), getFullName(), getPassword(), org);
    }
}

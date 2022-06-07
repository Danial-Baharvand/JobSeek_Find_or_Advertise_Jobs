package classes;

public class Recruiter extends User {
    String recruiterID;
    String org;
    String recruiterDescription;


    public Recruiter() {
        super("", "", "");
        recruiterID = "";
        org = "";
        recruiterDescription = "";
    }

    public Recruiter(String email, String password, String firstName, String lastName, String recruiterID, String org) {
        super(email, firstName, lastName);
        this.recruiterID = recruiterID;
        this.org = org;

    }
    public String getRecruiterID() {
        return recruiterID;
    }

    public String getOrg() {
        return org;
    }

    public void setRecruiterID(String recruiterID) {
        this.recruiterID = recruiterID;
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

package classes;

public class Recruiter extends User {
    String recruiterID;
    Organisation org;


    public Recruiter() {
        super("", "", "", "");
        recruiterID = "";
        Organisation org = new Organisation();
    }

    public Recruiter(String email, String password, String firstName, String lastName, String recruiterID, Organisation org) {
        super(email, password, firstName, lastName);
        this.recruiterID = recruiterID;
        this.org = org;

    }
    public String getRecruiterID() {
        return recruiterID;
    }

    public Organisation getOrg() {
        return org;
    }

    public void setRecruiterID(String recruiterID) {
        this.recruiterID = recruiterID;
    }

    public void setOrg(Organisation org) {
        this.org = org;
    }
}

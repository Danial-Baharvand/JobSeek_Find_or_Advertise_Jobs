package classes;

public class Recruiter extends User {

    private String organisation;

    public Recruiter(String email, String password, String firstName, String lastName, String organisation) {
        super(email, password, firstName, lastName);
        this.organisation = organisation;
    }
}

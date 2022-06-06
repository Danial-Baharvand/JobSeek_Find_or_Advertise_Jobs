package classes;

public class JobSeeker extends User {

    private String resumeFile;

    public JobSeeker(String email, String firstName, String lastName) {
        super(email, firstName, lastName);
    }

    public String toString() {
        return String.format("%s,%s,%s", super.getEmail(), super.getFirstName(), super.getLastName());
    }

}

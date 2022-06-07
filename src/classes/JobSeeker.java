package classes;

import java.util.ArrayList;

public class JobSeeker extends User {

    private String resumeFile;
    private ArrayList skills;

    public JobSeeker(String email, String fullName, String password) {
        super(email, fullName, password);
    }

    public String toString() {
        return String.format("%s,%s,%s", super.getEmail(), super.getFullName(), super.getPassword());
    }
}

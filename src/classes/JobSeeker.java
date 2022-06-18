package classes;

import java.util.*;
import java.util.stream.Collectors;

import static classes.Config.*;

public class JobSeeker extends User implements Inbox {
    private String resumeFile;
    Set<String> skills = new HashSet<>();
    Applications applications = new Applications();
    Invitations invitations = new Invitations();

    public JobSeeker(){
        super("","","");
        //this.skills = new HashSet<String>();
    }

    public JobSeeker(String email, String fullName, String password) {
        super(email, fullName, password);
    }

    public String toWriteFormat() {
        return   getEmail() + SEPARATOR_1 +
                getFullName() + SEPARATOR_1 +
                getPassword() + SEPARATOR_1 +
                isActive() + SEPARATOR_1 +
                resumeFile + SEPARATOR_1 +
                String.join(SEPARATOR_2, skills) + SEPARATOR_1 +
                applications() + SEPARATOR_1 +
                invitations();
    }

    public void addSkill(String skill){
        skills.add(skill);
    }

    public Set<String> getSkills() {
        return skills;
    }

    public String getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(String resumeFile) {
        this.resumeFile = resumeFile;
    }

    @Override
    public Applications applications() {
        return applications;
    }

    @Override
    public Invitations invitations() {
        return invitations;
    }
}

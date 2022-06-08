package classes;

import java.awt.*;
import java.util.*;

public class JobSeeker extends User {
    private String resumeFile;
    ArrayList<String> skills;

    public JobSeeker(){
        super("","","");
        skills = new ArrayList<>();
    }

    public JobSeeker(String email, String firstName, String lastName) {
        super(email, firstName, lastName);
    }

    public String toString() {
        return String.format("%s,%s,%s", super.getEmail(), super.getFullName(), super.getPassword());
    }

    public void addSkill(String skill){
        skills.add(skill);
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

}

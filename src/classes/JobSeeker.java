package classes;

import java.awt.*;
import java.util.*;

public class JobSeeker extends User {
    private String resumeFile;
    Set<String> skills;

    public JobSeeker(){
        super("","","");
        skills = new HashSet<String>();
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

    public Set<String> getSkills() {
        return skills;
    }

    public void deleteSkill(String skill) {
        this.skills.remove(skill);
    }


    /*public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }*/

    public String getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(String resumeFile) {
        this.resumeFile = resumeFile;
    }
}

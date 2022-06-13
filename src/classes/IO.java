package classes;

import com.google.common.collect.Sets;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import static classes.Config.*;

public class IO {

    public IO() {

    }

    public void newLine(String path, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clearFileContent(String path){
        try {
            PrintWriter pw = new PrintWriter(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void writeUser(User user){
        if (user instanceof JobSeeker){
            newLine(DT_JOBSEEKERS, user.toString());
        }else if (user instanceof Recruiter) {
            newLine(DT_RECRUITERS, user.toString());
        }else if (user instanceof Admin) {
            newLine(DT_RECRUITERS, user.toString());
        }
    }
    public HashMap<String, JobSeeker> readJobSeekers(BiMultiMap<String> skills){
        String line;
        HashMap<String, JobSeeker> jobSeekers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Config.DT_JOBSEEKERS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(",");
                JobSeeker jobSeeker = new JobSeeker(userData[EMAIL], userData[NAME], userData[PASSWORD]);
                jobSeeker.getSkills().addAll(skills.get(userData[EMAIL]));
                jobSeeker.setSkills(Sets.newHashSet(skills.get(jobSeeker.getEmail())));
                jobSeekers.putIfAbsent(userData[EMAIL], jobSeeker);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobSeekers;
    }
    public HashMap<String, Recruiter> readRecruiters(){
        String line;
        HashMap<String, Recruiter> recruiters = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_RECRUITERS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(",");
                Recruiter recruiter = new Recruiter(userData[EMAIL], userData[NAME], userData[PASSWORD],
                        userData[ORGANISATION]);
                recruiters.putIfAbsent(userData[EMAIL], recruiter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recruiters;
    }
    public HashMap<String, Admin> readAdmins(){
        String line;
        HashMap<String, Admin> admins = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DT_ADMINS))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split(",");
                Admin admin = new Admin(userData[EMAIL], userData[NAME], userData[PASSWORD]);
                admins.putIfAbsent(userData[EMAIL], admin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admins;
    }
}

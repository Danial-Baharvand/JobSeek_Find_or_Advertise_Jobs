package classes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Runtime {
    public static JFrame frame = new JFrame();

    public static void main(String[] args) {
        Application testSystem = new Application();
        LoginPage testLoginPage = new LoginPage();
        testSystem.userLogin(testLoginPage.getEmail(), testLoginPage.getPassword());
        System.out.println(testSystem.getCurrentUser());
        //showSearchPage(frame);
        showRecruiterProfilePage(frame);

    }
    public static void showSearchPage(JFrame frame){
        frame.setTitle("Search Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new SearchPage().getSearchPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void showSearchResultsPage(JFrame frame){
        frame.setTitle("Search Results Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new SearchResultsPage().getSearchResultsPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void showRecruiterProfilePage(JFrame frame){
        frame.setTitle("Recruiter Profile Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new RecruiterProfile().getRecruiterProfilePage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /*public static HashSet<Job> createExampleJobs(){
        Recruiter recruiter1 = new Recruiter();
        recruiter1.setOrg("Google");
        Job job1 = new Job();
        job1.setJobTitle("Software Engineer");
        job1.setRecruiter(recruiter1);
        Job job2 = new Job();
        job2.setJobTitle("It Specialist");
        job2.setRecruiter(recruiter1);
        Job job3 = new Job();
        job3.setJobTitle("Data Analyst");
        job3.setRecruiter(recruiter1);
        return new HashSet<Job>(Arrays.asList(job1, job2, job3));
    }*/
}
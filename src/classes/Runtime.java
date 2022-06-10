package classes;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;

public class Runtime {
    public static JFrame frame = new JFrame();
    public static Tests testObject = new Tests();
    private static AccountManagement accMan;
    public static Categories categories = new Categories();
    private static Jobs jobs = new Jobs();

    public static void main(String[] args) {
        accMan = new AccountManagement();
        accMan.setCurrentUser(new Recruiter("hulk@gmail.com", "bruce", "1234", "Google"));
        //currentApplication = new Application();
        //currentApplication.getRegisteredUsers();

        // TEST ACCOUNT CREATION
        //CreateAccount testCreateAccount = new CreateAccount();
        //testObject.testCreateAccount(testCreateAccount, currentApplication);

        // TEST LOGIN
        //LoginPage testLoginPage = new LoginPage();
        //testObject.testLogin(testLoginPage, testApplication);


        // SHOW SEARCH PAGE
        showSearchPage(frame);

        //SHOW Create Job PAGE
        //showCreateJobPage(frame);

        //SHOW Edit Category Page
        //showEditCategoryPage(frame, new CreateJobPage());

        //SHOW LOGIN PAGE
        //showLoginPage(frame);

        //SHOW CREATE ACCOUNT PAGE
        //showCreateAccountPage(frame);

        //SHOW RECRUITER PROFILE PAGE
        //showRecruiterProfilePage(frame);

        //SHOW JOB SEEKER PROFILE
        //showJobSeekerHome(frame);

    }

    public static void showSearchPage(JFrame frame) {
        frame.setTitle("Search Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new SearchPage(testObject.createExampleJobs()).getSearchPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void showCreateJobPage(JFrame frame) {
        frame.setTitle("Create Job Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new CreateJobPage().getCreateJobPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void showEditCategoryPage(JFrame frame, CreateJobPage jobPage) {
        frame.setTitle("Edit Categories");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new EditCategoryPage(frame, jobPage).getCatPagePanel());
        frame.pack();
        frame.setVisible(true);
    }

    public static void showLoginPage(JFrame frame) {
        frame.setTitle("Login Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new LoginPage(frame, accMan).getLoginPagePanel());
        frame.pack();
        frame.setVisible(true);
    }

    public static void showCreateAccountPage(JFrame frame) {
        frame.setTitle("Create Account Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new CreateAccountPage(frame).getCreateAccountPanel());
        frame.pack();
        frame.setVisible(true);
    }

    public static void showRecruiterProfilePage(JFrame frame){
        frame.setTitle("Recruiter Profile Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new RecruiterProfilePage().getRecruiterProfilePage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void showJobSeekerHome(JFrame frame){
        frame.setTitle("Job Seeker Home");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new JobSeekerProfilePage().getJobSeekerProfile());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void showSearchResultsPage(JFrame frame, Search search){
        frame.setTitle("Search Results Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new SearchResultsPage(search).getSearchResultsPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static AccountManagement accountManager() {
        return accMan;
    }

    public static Jobs getJobs() {
        return jobs;
    }
}
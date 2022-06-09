package classes;

import javax.swing.*;

public class Runtime {
    public static JFrame frame = new JFrame();
    public static Tests testObject = new Tests();
    public static AccountManagement accMan;

    public static void main(String[] args) {
        accMan = new AccountManagement();
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

}
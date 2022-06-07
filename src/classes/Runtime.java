package classes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Runtime {
    public static JFrame frame = new JFrame();
    public static Tests testObject = new Tests();
    public static Application currentApplication;

    public static void main(String[] args) {
        //currentApplication = new Application();
        //currentApplication.getRegisteredUsers();

        // TEST ACCOUNT CREATION
        //CreateAccount testCreateAccount = new CreateAccount();
        //testObject.testCreateAccount(testCreateAccount, currentApplication);

        // TEST LOGIN
        //LoginPage testLoginPage = new LoginPage();
        //testObject.testLogin(testLoginPage, testApplication);


        // SHOW SEARCH PAGE
        //showSearchPage(frame);

        //SHOW LOGIN PAGE
        //showLoginPage(frame);

        //SHOW CREATE ACCOUNT PAGE
        showCreateAccountPage(frame);

        //SHOW RECRUITER PROFILE PAGE
        showRecruiterProfilePage(frame);


    }

    public static boolean actionUserLogin(String email, String password) {
        return currentApplication.userLogin(email, password);
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

    public static void showLoginPage(JFrame frame) {
        frame.setTitle("Login Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new LoginPage().getLoginPagePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void showCreateAccountPage(JFrame frame) {
        frame.setTitle("Create Account Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new CreateAccount().getCreateAccountPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void actionShowCreateAccountPage() {
        showCreateAccountPage(frame);
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
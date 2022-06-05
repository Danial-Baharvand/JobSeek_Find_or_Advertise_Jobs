package classes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Runtime {
    public static JFrame frame = new JFrame();
    public static Tests testObject = new Tests();

    public static void main(String[] args) {
        Application testApplication = new Application();
        testApplication.getRegisteredUsers();

        // TEST ACCOUNT CREATION
        CreateAccount testCreateAccount = new CreateAccount();
        testObject.testCreateAccount(testCreateAccount, testApplication);

        // TEST LOGIN
        //LoginPage testLoginPage = new LoginPage();
        //testObject.testLogin(testLoginPage, testApplication);


        // SHOW SEARCH PAGE
        //showSearchPage(frame);

    }
    public static void showSearchPage(JFrame frame){
        frame.setTitle("Search Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new SearchPage(testObject.createExampleJobs()).getSearchPanel());
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
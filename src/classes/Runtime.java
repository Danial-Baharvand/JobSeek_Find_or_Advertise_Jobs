package classes;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Runtime {
    public static JFrame frame = new JFrame();
    public static Tests testObject = new Tests();
    private static AccountManagement accMan;


    /**
     * pagesVisited is a static attribute that is used at Runtime to create a LIFO structure of the pages visited by the user
     * Each time a page is navigated away from, that page is pushed to the front of the deque
     * Each time a user navigates backwards from a page using the "Back" button, the page at the front of the deque is popped
     * The backwards navigation is implemented by the navigateBack method
     */
    private static Deque<String> pagesVisited = new ArrayDeque<String>();

    /**
     * currentSearch is an object of Search
     * currentSearch is used to facilitate returning to the SearchResultsPage via the "Back" button
     * when a new Search is made, currentSearch is set to that Search object and overwritten when a new Search is made
     */

    private static Search currentSearch; // Look into making non-static

    public static void main(String[] args) {
        accMan = new AccountManagement();
        //accMan.setCurrentUser(new Recruiter("hulk@gmail.com", "bruce", "1234", "Google"));
        accMan.setCurrentUser(new JobSeeker("hulk@gmail.com", "bruce", "1234"));

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
        showJobSeekerHome(frame);


    }

    public static void showSearchPage(JFrame frame) {
        frame.setTitle("Search Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new SearchPage(Tests.createExampleJobs()).getSearchPanel());
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

    public static void showLoginPage(JFrame frame, String navigatedFrom) {
        pagesVisited.push(navigatedFrom);
        frame.setTitle("Login Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new LoginPage(frame, accMan).getLoginPagePanel());
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

    public static void showCreateAccountPage(JFrame frame, String navigatedFrom) {
        pagesVisited.push(navigatedFrom);
        frame.setTitle("Create Account Page");
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setContentPane(new CreateAccountPage(frame).getCreateAccountPanel());
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
        frame.setContentPane(new JobSeekerHomePage().getJobSeekerProfile());
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


    /**
     * navigateBack is called when the "Back" button is pressed
     * the first element in the pagesVisited deque is popped and stored in a String variable called lastPage
     * the String variable called lastPage is used to determine the page to display
     */
    public static void navigateBack() {
        String lastPage = pagesVisited.pop();
        switch (lastPage) {
            case "ApplicantPage":
                System.out.println("Cannot navigate to ApplicantPage yet. Must be set up in Runtime.navigateBack().");
                break;
            case "CreateAccountPage":
                showCreateAccountPage(frame);
                break;
            case "CreateJobPage":
                showCreateJobPage(frame);
                break;
            case "DescriptionPage":
                System.out.println("Cannot navigate to DescriptionPage yet. Must be set up in Runtime.navigateBack().");
                break;
            case "EditCategoryPage":
                System.out.println("Cannot navigate to EditCategoryPage yet. Must be set up in Runtime.navigateBack().");
                break;
            case "JobEditPage":
                System.out.println("Cannot navigate to JobEditPage yet. Must be set up in Runtime.navigateBack().");
                break;
            case "JobSeekerProfilePage":
                showJobSeekerHome(frame);
                break;
            case "LoginPage":
                showLoginPage(frame);
                break;
            case "RecruiterHomePage":
                System.out.println("Cannot navigate to RecruiterHomePage yet. Must be set up in Runtime.navigateBack().");
                break;
            case "RecruiterProfilePage":
                showRecruiterProfilePage(frame);
                break;
            case "SearchPage":
                showSearchPage(frame);
                break;
            case "SearchResultsPage":
                showSearchResultsPage(frame, currentSearch);
                break;
        }
    }
}
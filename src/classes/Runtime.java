package classes;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Runtime {
    private static JFrame frame = new JFrame();
    private static Header header;
    private static AccountManagement accMan;


    /**
     * pagesVisited is a static attribute that is used at Runtime to create a LIFO structure of the pages visited by the user
     * Each time a page is navigated away from, that page is pushed to the front of the deque
     * Each time a user navigates backwards from a page using the "Back" button, the page at the front of the deque is popped
     * The backwards navigation is implemented by the navigateBack method
     */
    private static Deque<Page> pagesVisited = new ArrayDeque<>();

    /**
     * currentSearch is an object of Search
     * currentSearch is used to facilitate returning to the SearchResultsPage via the "Back" button
     * when a new Search is made, currentSearch is set to that Search object and overwritten when a new Search is made
     */

    private static Search currentSearch; // Look into making non-static

    public static void main(String[] args) {
        setLookAndFeel();
        header = new Header();
        accMan = new AccountManagement();
        //accMan.setCurrentUser(accMan.getRecruiters().get("hulk@gmail.com"));
        //accMan.setCurrentUser(accMan.getJobSeekers().get("hulk@gmail.com"));

        //currentApplication = new Application();
        //currentApplication.getRegisteredUsers();

        // TEST ACCOUNT CREATION
        //CreateAccount testCreateAccount = new CreateAccount();
        //testObject.testCreateAccount(testCreateAccount, currentApplication);

        // TEST LOGIN
        //LoginPage testLoginPage = new LoginPage();
        //testObject.testLogin(testLoginPage, testApplication);

        //SHOW LOGIN PAGE
        //showLoginPage();

        // SHOW SEARCH PAGE
        showSearchPage();
        accMan.setCurrentUser(accMan.getJobSeekers().get("hulk@gmail.com"));


        //SHOW RECRUITER HOME PAGE
        //accMan.setCurrentUser(accMan.getRecruiters().get("hulk@gmail.com"));
        //showRecruiterHome();


        //SHOW JOB SEEKER HOME Page
        //accMan.setCurrentUser(accMan.getJobSeekers().get("hulk@gmail.com"));
        //showJobSeekerHome();


        //SHOW Create Job PAGE
        //showCreateJobPage();
        //accMan.setCurrentUser(accMan.getRecruiters().get("hulk@gmail.com"));

        //SHOW Edit Category Page
        //showEditCategoryPage( new CreateJobPage());
        //accMan.setCurrentUser(accMan.getRecruiters().get("hulk@gmail.com"));

        //SHOW CREATE ACCOUNT PAGE
        //showCreateAccountPage();

        //SHOW RECRUITER PROFILE PAGE
        //accMan.setCurrentUser(accMan.getRecruiters().get("hulk@gmail.com"));
        //showRecruiterProfilePage();





    }

    public static void showSearchPage() {
        showPage("Search Page", new SearchPage());
    }
    public static void showCreateJobPage() {
        showPage("Create Job Page", new CreateJobPage());
    }
    public static void showEditCategoryPage(CreateJobPage jobPage) {
        showPage("Edit Categories", new EditCategoryPage(jobPage));
    }
    public static void showLoginPage() {
        showPage("Login Page", new LoginPage(accMan));
    }
    public static void showAppliedJobsPage() {
        showPage("Applied Jobs", new AppliedJobsPage());
    }
    public static void showCreateAccountPage( String navigatedFrom) {
        showPage("Create Account Page", new CreateAccountPage());
    }
    public static void showCreateAccountPage() {
        showPage("Create Account Page", new CreateAccountPage());
    }
    public static void showRecruiterProfilePage(){
        showPage("Recruiter Profile Page", new RecruiterProfilePage(Tests.createExampleRecruiter()));
    }
    public static void showJobSeekerHome(){
        showPage("Job Seeker Home", new JobSeekerHomePage());
    }
    public static void showRecruiterHome(){
        showPage("Recruiter Home", new RecruiterHomePage());
    }
    public static void showSearchResultsPage(ArrayList<ScoredJob> jobList){
        showPage("Search Results Page", new SearchResultsPage(jobList));
    }
    public static void showPage(String pageTitle, Page page){
        frame.setTitle(pageTitle);
        frame.getContentPane().removeAll();
        frame.repaint();
        pagesVisited.add(page);
        header.updateButtons();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(header.getPanel());
        panel.add(page.getPanel());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static AccountManagement accountManager() {
        return accMan;
    }
    public static void setLookAndFeel(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Nimbus isn't availible!");
        }
    }


    public static Header getHeader() {
        return header;
    }

    public static Deque<Page> getPagesVisited() {
        return pagesVisited;
    }
}
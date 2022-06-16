package classes;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.awt.*;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JFrame;

public class Runtime {
    public static final JFrame frame = new JFrame();
    private static Header header;
    private static AccountManagement accMan;
    private static Dimension screenSize;


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
        screenSize = getPageSize();
        frame.setLocation(screenSize.width/2, screenSize.height/2);
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
        //accMan.setCurrentUser(accMan.getJobSeekers().get("hulk@gmail.com"));
        //showSearchPage();



        //SHOW RECRUITER HOME PAGE
        accMan.setCurrentUser(accMan.getRecruiters().get("fury@recruiter.com"));
        showRecruiterHome();


        //SHOW JOB SEEKER HOME Page
        //accMan.setCurrentUser(accMan.getJobSeekers().get("hulk@gmail.com"));
        //showJobSeekerHome();


        //SHOW Create Job PAGE
        //accMan.setCurrentUser(accMan.getRecruiters().get("fury@recruiter.com"));
        //showCreateJobPage();


        //SHOW Edit Category Page
        //showEditCategoryPage( new CreateJobPage());
        //accMan.setCurrentUser(accMan.getRecruiters().get("hulk@gmail.com"));

        //SHOW CREATE ACCOUNT PAGE
        //showCreateAccountPage();

        //SHOW RECRUITER PROFILE PAGE
        //accMan.setCurrentUser(accMan.getRecruiters().get("fury@recruiter.com"));
        //showRecruiterProfilePage();

        //SHOW ADMIN HOME Page
        //accMan.setCurrentUser(accMan.getAdmins().get("admin@admin.com"));
        //showAdminHomePage();



        Thesaurus testRequest = new Thesaurus("bottle", "en_US", "json");
    }

    public static void showSearchPage() {
        showPage( new SearchPage());
    }
    public static void showCreateJobPage() {
        showPage( new CreateJobPage());
    }
    public static void showCreateJobPage(Job job) {
        showPage( new CreateJobPage(job));
    }
    public static void showDescriptionPage(Job job) {
        showPage( new DescriptionPage(job));
    }
    public static void showEditCategoryPage(String category) {
        showPage( new EditCategoryPage(category));
    }
    public static void showLoginPage() {
        showPage( new LoginPage(accMan));
    }
    public static void showJobSeekerResultsPage(HashMap<JobSeeker, Integer> jobSeekerScores, Job job) {
        showPage( new JobSeekerResultsPage(jobSeekerScores, job));
    }
    public static void showAppliedJobsPage() {
        showPage( new AppliedJobsPage());
    }
    public static void showCreateAccountPage( String navigatedFrom) {
        showPage( new CreateAccountPage());
    }
    public static void showCreateAccountPage() {
        showPage( new CreateAccountPage());
    }
    public static void showRecruiterProfilePage(){
        showPage( new RecruiterProfilePage(Tests.createExampleRecruiter()));
    }
    public static void showJobSeekerHome(){
        showPage( new JobSeekerHomePage());
    }
    public static void showRecruiterHome(){
        showPage( new RecruiterHomePage());
    }
    public static void showSearchResultsPage(ArrayList<ScoredJob> jobList){
        showPage(new SearchResultsPage(jobList));
    }
    public static void showAdminHomePage(){
        showPage(new AdminHomePage());
    }
    public static void showPage(Page page){
        frame.setTitle(page.pageName());
        frame.getContentPane().removeAll();
        frame.repaint();
        if (pagesVisited.isEmpty()||!pagesVisited.peekLast().getClass().equals(page.getClass())){
            pagesVisited.add(page);
        }else {
            pagesVisited.removeLast();
            pagesVisited.add(page);
        }
        header.updateButtons();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));




        JPanel pagePanel = page.getPanel();
        pagePanel.setPreferredSize(screenSize);



        panel.add(header.getPanel());
        panel.add(pagePanel);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void showPreviousPage(){
        pagesVisited.removeLast();
        showPage(pagesVisited.removeLast());
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
    private static Dimension getPageSize(){
        Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = screenSize.width/2;
        int height = screenSize.height/2;
        return new Dimension(width, height);
    }

    public static Header getHeader() {
        return header;
    }

    public static Deque<Page> getPagesVisited() {
        return pagesVisited;
    }
}
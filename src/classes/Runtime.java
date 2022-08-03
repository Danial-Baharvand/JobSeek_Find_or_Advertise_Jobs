package classes;

import com.google.common.collect.TreeMultimap;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.Toolkit;
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
     */
    private static Deque<Page> pagesVisited = new ArrayDeque<>();

    public static void main(String[] args) {
        setLookAndFeel();
        screenSize = getPageSize();
        frame.setLocation(screenSize.width/2, screenSize.height/2);
        header = new Header();
        accMan = new AccountManagement();
        showSearchPage();
    }
    // code to open pages
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
    public static void showEditCategoryPage(Category category) {
        showPage( new EditCategoryPage(category));
    }
    public static void showLoginPage() {
        showPage( new LoginPage(accMan));
    }
    public static void showJobSeekerResultsPage(TreeMultimap<Integer, JobSeeker> jobSeekerScores, Job job, String title) {
        showPage( new JobSeekerResultsPage(jobSeekerScores, job, title));
    }

    public static void showCreateAccountPage( String navigatedFrom) {
        showPage( new CreateAccountPage());
    }
    public static void showCreateAccountPage() {
        showPage( new CreateAccountPage());
    }

    public static void showJobSeekerHome(){
        showPage( new JobSeekerHomePage());
    }
    public static void showRecruiterHome(){
        showPage( new RecruiterHomePage());
    }
    public static void showSearchResultsPage(TreeMultimap<Integer, Job> jobList){
        showPage(new SearchResultsPage(jobList));
    }
    public static void showAdminHomePage(){
        showPage(new AdminHomePage());
    }

    /**
     * shows a page in correct format
     * @param page to be shown
     */
    public static void showPage(Page page){
        frame.setTitle(page.pageName());
        // refresh page
        frame.getContentPane().removeAll();
        frame.repaint();
        // handle navigation
        if (pagesVisited.isEmpty()||!pagesVisited.peekLast().getClass().equals(page.getClass())){
            pagesVisited.add(page);
        }else {
            pagesVisited.removeLast();
            pagesVisited.add(page);
        }
        // update header
        header.updateButtons();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel pagePanel = page.getPanel();
        pagePanel.setPreferredSize(screenSize);// set size to half of screen
        // add header and page content
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
        // try to make the program look pretty
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
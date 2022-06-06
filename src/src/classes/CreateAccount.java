package classes;

import javax.swing.*;
import java.awt.*;

public class CreateAccount {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    final String[] type = new String[] {"Job Seeker", "Recruiter"};
    private String organisation;
    private String resumeFile;
    private JLabel typeLabel;
    private JFrame frame;
    private JComboBox userTypeComboBox;
    private JLabel fullNameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField fullNameTextField;
    private JTextField emailTextField;
    private JLabel confirmPasswordLabel;
    private JPasswordField passwordPasswordField;
    private JPasswordField confirmPasswordPasswordField;
    private JButton createAccountButton;
    private JPanel mainPanel;

    public CreateAccount() {
        frame = new JFrame();
        frame.setTitle("Login Page");

    }

    /**
     * creates a new JobSeeker after validating that the email is new and that the passwords match
     * writes the email, firstName and lastName to the JobSeekers file
     * writes the email and password to the registeredUsers file
     * @param firstName of the JobSeeker
     * @param lastName of the JobSeeker
     * @param email of the JobSeeker
     * @param password of the JobSeeker
     * @param confirmPassword of the JobSeeker
     */
    public String createJobSeeker(String firstName, String lastName, String email, String password, String confirmPassword) {
        //check if email is registered to an existing account - if so, notify that email is not new
        if (!new Application().isRegisteredUser(email)) {
            //check if password and confirm password match - if not, return that passwords don't match
            if (password.equals(confirmPassword)) {
                //create new JobSeeker object and add new JobSeeker to database
                User newJobSeeker = new JobSeeker(email, firstName, lastName);
                CSVWriter writer = new CSVWriter();
                String newJobSeekerDetails = String.format("%s,%s,%s", newJobSeeker.getEmail(), newJobSeeker.getFirstName(),newJobSeeker.getLastName());
                writer.newLine(Application.dt_jobSeekers, newJobSeekerDetails);
                //add new email and password to registered users file
                String newRegisteredUserDetails = String.format("%s,%s", newJobSeeker.getEmail(), password);
                writer.newLine(Application.dt_registeredUsers, newRegisteredUserDetails);
                System.out.println(String.format("New account successfully created for %s!", email));
                return String.format("New account successfully created for %s!", email);
            } else {
                System.out.println(String.format("Passwords do not match. Account not created. Please try again."));
                return String.format("Passwords do not match. Account not created. Please try again.");
            }
        } else {
            System.out.println(String.format("That email address has already been registered to an account. Please login with your email address and password."));
            return String.format("That email address has already been registered to an account. Please login with your email address and password.");
        }
    }
    /**
     * creates a new JobSeeker after validating that the email is new and that the passwords match
     * writes the email, firstName and lastName to the JobSeekers file
     * writes the email and password to the registeredUsers file
     * writes the resumeFile to the resumes file
     * @param firstName of the JobSeeker
     * @param lastName of the JobSeeker
     * @param email of the JobSeeker
     * @param password of the JobSeeker
     * @param confirmPassword of the JobSeeker
     * @param resumeFile is the file path of the JobSeeker's resume
     */
    public void createJobSeeker(String firstName, String lastName, String email, String password, String confirmPassword, String resumeFile) {
        //check if email is new (i.e., not in registered users) - if not, notify that email is not new
        //check if password and confirm password match - if not, return that passwords don't match
        //create new JobSeeker object and add new JobSeeker to database
        User newJobSeeker = new JobSeeker(email, firstName, lastName);
        CSVWriter writer = new CSVWriter();
        String newJobSeekerDetails = newJobSeeker.toString();
        writer.newLine(Application.dt_jobSeekers, newJobSeekerDetails);
        //add new email and password to registered users file
        String newRegisteredUserDetails = newJobSeeker.getEmail().concat(",").concat(newJobSeeker.getPassword());
        writer.newLine(Application.dt_registeredUsers, newRegisteredUserDetails);
        //add new resume to resumes file
        writer.newLine(Application.dt_resumes, resumeFile);
    }

    public JPanel getCreateAccountPanel() {
        return mainPanel;
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }
}

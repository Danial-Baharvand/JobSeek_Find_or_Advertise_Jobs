package classes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CreateAccountPage {
    private final JFrame frame;
    private JComboBox userTypeComboBox;
    private ClearingTextField fullNameTextField;
    private ClearingTextField emailTextField;
    private ClearingPasswordField passwordPasswordField;
    private ClearingPasswordField confirmPasswordPasswordField;
    private JButton createAccountButton;
    private JPanel mainPanel;
    private JLabel orgLabel;
    private JTextField orgTextField;
    private JMenuBar menuBar;
    private JButton backButton;
    private JButton homeButton;

    public CreateAccountPage(JFrame frame) {
        this.frame = frame;

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createJobSeeker();
                Runtime.showLoginPage(frame, "CreateAccountPage");
            }
        });
        userTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (userTypeComboBox.getSelectedItem().equals("JobSeeker")) {
                    orgLabel.setVisible(false);
                    orgTextField.setVisible(false);
                } else if (userTypeComboBox.getSelectedItem().equals("Recruiter")) {
                    orgLabel.setVisible(true);
                    orgTextField.setVisible(true);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime.navigateBack();
            }
        });
    }

    /**
     * creates a new JobSeeker after validating that the email is new and that the passwords match
     * writes the email, firstName and lastName to the JobSeekers file
     * writes the email and password to the registeredUsers file
     *
     * @param fullName        of the JobSeeker
     * @param email           of the JobSeeker
     * @param password        of the JobSeeker
     * @param confirmPassword of the JobSeeker
     */
    public String createJobSeeker() {
        final String email = emailTextField.getText();
        final String name = fullNameTextField.getText();
        final String pass = String.valueOf(passwordPasswordField.getPassword());
        final String passConfirm = String.valueOf(confirmPasswordPasswordField.getPassword());
        final String org = orgTextField.getText();
        //note: refactored this from line 76 (if (userTypeComboBox.getSelectedItem().toString().equals(JobSeeker.class.getSimpleName()))
        // to make the user type accessible for the purposes of navigation
        final String userType = userTypeComboBox.getSelectedItem().toString();
        //check if email is registered to an existing account - if so, notify that email is not new
        if (!Runtime.accountManager().isRegisteredUser(email)) {
            //check if password and confirm password match - if not, return that passwords don't match
            if (pass.equals(passConfirm)) {
                User newUser;
                if (userType.equals(JobSeeker.class.getSimpleName())) {
                    //create new JobSeeker object and add new JobSeeker to database
                    newUser = new JobSeeker(email, name, pass);
                } else {
                    //create new Recruiter object and add new Recruiter to database
                    newUser = new Recruiter(email, name, pass, org);
                }

                IO writer = new IO();
                writer.writeUser(newUser);
                Runtime.accountManager().addUser(newUser);
                System.out.printf("New %s account successfully created for %s!%n", email);
                return String.format("New account successfully created for %s!", email);
            } else {
                System.out.println("Passwords do not match. Account not created. Please try again.");
                return "Passwords do not match. Account not created. Please try again.";
            }
        } else {
            System.out.println("That email address has already been registered to an account. Please login with your email address and password.");
            return "That email address has already been registered to an account. Please login with your email address and password.";
        }
    }

    /**
     * creates a new JobSeeker after validating that the email is new and that the passwords match
     * writes the email, firstName and lastName to the JobSeekers file
     * writes the email and password to the registeredUsers file
     * writes the resumeFile to the resumes file
     *
     * @param firstName       of the JobSeeker
     * @param lastName        of the JobSeeker
     * @param email           of the JobSeeker
     * @param password        of the JobSeeker
     * @param confirmPassword of the JobSeeker
     * @param resumeFile      is the file path of the JobSeeker's resume
     */
 /*   public void createJobSeeker(String firstName, String lastName, String email, String password, String confirmPassword, String resumeFile) {
        //check if email is new (i.e., not in registered users) - if not, notify that email is not new
        //check if password and confirm password match - if not, return that passwords don't match
        //create new JobSeeker object and add new JobSeeker to database
        User newJobSeeker = new JobSeeker(email, firstName, lastName);
        IO writer = new IO();
        String newJobSeekerDetails = newJobSeeker.toString();
        writer.newLine(AccountManagement.dt_jobSeekers, newJobSeekerDetails);
        //add new email and password to registered users file
        String newRegisteredUserDetails = newJobSeeker.getEmail().concat(",").concat(newJobSeeker.getPassword());
        writer.newLine(AccountManagement.dt_registeredUsers, newRegisteredUserDetails);
        //add new resume to resumes file
        writer.newLine(AccountManagement.dt_resumes, resumeFile);
    }*/
    public JPanel getCreateAccountPanel() {
        return mainPanel;
    }


}

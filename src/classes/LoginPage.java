package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Locale;

public class LoginPage extends JFrame {

    private JPanel mainPanel;
    private final JFrame frame;
    private ClearingTextField emailClearingTextField;
    private ClearingPasswordField passwordClearingPasswordField;
    private JButton loginButton;
    private JButton createNewAccountButton;
    private JTextField textField1;
    private JButton backButton;
    private JButton searchButton;
    private boolean passClicked = false;

    public LoginPage(JFrame frame, AccountManagement accMan) {
        this.frame = frame;

        /**
         * @param email is String that is a User's email
         * @param password is a String that is a User's password
         * @return true if the email and password parameters match a key:value pair in the registeredUsers HashMap
         * else false
         */

        /**
         * if the user clicks the Login button, the method gets the user input for email and password
         * the method invokes the actionUserLogin method of Runtime, which invokes the userLogin method of the Application class
         * email and password are passed as arguments to actionUserLogin and then to userLogin
         * actionUserLogin and userLogin both return true if the user is logged-in, which is used in the method to notify the user
         */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userLogin(accMan)) {
                    JOptionPane.showMessageDialog(null, "You have successfully logged in!");
                } else {
                    JOptionPane.showMessageDialog(null, "The username of password is incorrect.");
                }
            }
        });
        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime.showCreateAccountPage(frame, "LoginPage");
            }
        });
        passwordClearingPasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (!passClicked) {
                    passwordClearingPasswordField.setText("");
                    passClicked = true;
                }
            }
        });
    }

    public boolean userLogin(AccountManagement accMan) {
        if (validateUser(accMan.getJobseekers())) {
            accMan.setCurrentUser(accMan.getJobseekers().get(emailClearingTextField.getText()));
            return true;
        } else if (validateUser(accMan.getRecruiters())) {
            accMan.setCurrentUser(accMan.getRecruiters().get(emailClearingTextField.getText()));
            return true;
        } else if (validateUser(accMan.getAdmins())) {
            accMan.setCurrentUser(accMan.getAdmins().get(emailClearingTextField.getText()));
            return true;
        } else return false;
    }

    private boolean validateUser(HashMap<String, ? extends User> users) {
        if (users.containsKey(emailClearingTextField.getText())) {
            return users.get(emailClearingTextField.getText()).getPassword().equals(String.valueOf(passwordClearingPasswordField.getPassword()));
        } else return false;
    }

    public JPanel getLoginPagePanel() {
        return mainPanel;
    }

}
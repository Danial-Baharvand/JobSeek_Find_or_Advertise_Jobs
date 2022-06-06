package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    private JPanel mainPanel;
    private JFrame frame;
    private JTextField emailTextField;
    private JLabel emailLabel;
    private JTextField passwordTextField;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JButton createNewAccountButton;

    public LoginPage() {

        frame = new JFrame();
        frame.setTitle("Login Page");

        /**
         * if the user clicks the Login button, the method gets the user input for email and password
         * the method invokes the actionUserLogin method of Runtime, which invokes the userLogin method of the Application class
         * email and password are passed as arguments to actionUserLogin and then to userLogin
         * actionUserLogin and userLogin both return true if the user is logged-in, which is used in the method to notify the user
         */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailTextField.getText();
                String password = passwordTextField.getText();
                if (Runtime.actionUserLogin(email, password)) {
                    JOptionPane.showMessageDialog(null, "You have successfully logged in!");
                } else {
                    JOptionPane.showMessageDialog(null, "The username of password is incorrect.");
                }
            }
        });
        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime.actionShowCreateAccountPage();
            }
        });
    }

    public JPanel getLoginPagePanel() {
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
package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;

public class CreateAccountPage implements Page {
    private JComboBox userTypeComboBox;
    private ClearingTextField fullNameTextField;
    private ClearingTextField emailTextField;
    private ClearingPasswordField passwordPasswordField;
    private ClearingPasswordField confirmPasswordPasswordField;
    private JButton createAccountButton;
    private JPanel mainPanel;
    private JLabel orgLabel;
    private ClearingTextField orgTextField;
    private JButton backButton;

    public CreateAccountPage() {

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (createJobSeeker()) {
                        Runtime.showLoginPage();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                }
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
                Runtime.frame.repaint();
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
    public boolean createJobSeeker() throws Exception {
        final String email = emailTextField.forceGetText();
        final String name = fullNameTextField.forceGetText();
        final String pass = String.valueOf(passwordPasswordField.getPassword());
        final String passConfirm = String.valueOf(confirmPasswordPasswordField.getPassword());
        final String org = orgTextField.forceGetText();
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
                writer.writeToDB(newUser);
                Runtime.accountManager().addUser(newUser);
                JOptionPane.showMessageDialog(null, String.format("New %s account successfully created " +
                        "for %s!%n", userTypeComboBox.getSelectedItem().toString(), email));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Passwords do not match. " +
                        "Account not created. Please try again.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "That email address has already been " +
                    "registered to an account. Please login with your email address and password.");
            return false;
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
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 3, new Insets(20, 20, 20, 20), -1, -1));
        mainPanel.setBackground(new Color(-13224648));
        mainPanel.setMinimumSize(new Dimension(299, 304));
        mainPanel.setPreferredSize(new Dimension(384, 600));
        fullNameTextField = new ClearingTextField();
        fullNameTextField.setBackground(new Color(-1973791));
        fullNameTextField.setForeground(new Color(-8355712));
        fullNameTextField.setText("Full Name");
        mainPanel.add(fullNameTextField, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), new Dimension(1000, -1), 0, false));
        emailTextField = new ClearingTextField();
        emailTextField.setBackground(new Color(-1973791));
        emailTextField.setForeground(new Color(-8355712));
        emailTextField.setText("E-Mail");
        mainPanel.add(emailTextField, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), new Dimension(1000, -1), 0, false));
        passwordPasswordField = new ClearingPasswordField();
        passwordPasswordField.setBackground(new Color(-1973791));
        passwordPasswordField.setForeground(new Color(-8355712));
        passwordPasswordField.setText("Password");
        mainPanel.add(passwordPasswordField, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), new Dimension(1000, -1), 0, false));
        createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        mainPanel.add(createAccountButton, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, new Dimension(150, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        confirmPasswordPasswordField = new ClearingPasswordField();
        confirmPasswordPasswordField.setBackground(new Color(-1973791));
        confirmPasswordPasswordField.setForeground(new Color(-8355712));
        confirmPasswordPasswordField.setText("Confirm Password");
        mainPanel.add(confirmPasswordPasswordField, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), new Dimension(1000, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Calibri Light", -1, 22, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-592138));
        label1.setText("Create Account");
        mainPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-592138));
        label2.setText("Profile Type");
        mainPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        orgLabel = new JLabel();
        Font orgLabelFont = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, orgLabel.getFont());
        if (orgLabelFont != null) orgLabel.setFont(orgLabelFont);
        orgLabel.setForeground(new Color(-592138));
        orgLabel.setText("Organisation");
        orgLabel.setVisible(false);
        mainPanel.add(orgLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        userTypeComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("JobSeeker");
        defaultComboBoxModel1.addElement("Recruiter");
        userTypeComboBox.setModel(defaultComboBoxModel1);
        mainPanel.add(userTypeComboBox, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(300, -1), new Dimension(300, -1), 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    @Override
    public void update() {
        //Do nothing
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    @Override
    public String pageName() {
        return "Create Account";
    }
}

package classes.pages;

import javax.swing.*;
import java.awt.*;

public class LoginPage {

    public String email;
    public String password;

    public LoginPage() {
        // registeredUsers is instantiated with valid user for testing purposes. Later, it will accept user input.
        this.email = "danial@example.com";
        this.password = "abczyx";
        // registeredUsers is instantiated with invalid email for testing purposes.
        //this.email = "daniel@example.com";
        //this.password = "abc123";
        // registeredUsers is instantiated with valid email but invalid password for testing purposes.
        //this.email = "danial@example.com";
        //this.password = "abc123";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
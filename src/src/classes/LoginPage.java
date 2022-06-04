package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {

    public String email;
    public String password;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JButton loginButton;
    private JButton createNewAccountButton;

    public LoginPage() {
        // registeredUsers is instantiated with valid user for testing purposes. Later, it will accept user input.
        this.email = "jack2@example.com";
        this.password = "abc123";
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

}

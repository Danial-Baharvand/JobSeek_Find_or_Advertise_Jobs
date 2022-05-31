package classes;

import java.util.HashMap;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Application {

    public HashMap<String, String> registeredUsers = new HashMap<String, String>();
    private String currentUser;
    String dataTable = "/Users/jackstoneman/JavaProjects/group-i/out/production/src/dataTables/registeredUsers.csv";

    public Application() {
        this.readRegisteredUsers();
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String email) {
        this.currentUser = email;
    }

    /**
     * @param email is String that is a User's email
     * @param password is a String that is a User's password
     * @return true if the email and password parameters match a key:value pair in the registeredUsers HashMap
     * else false
     */
    public boolean userLogin(String email, String password) {
        if (this.registeredUsers.containsValue(this.registeredUsers.get(email))) {
            this.setCurrentUser(email);
            return true;
        } else {
            return false;
        }
    }

    private void readRegisteredUsers() {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(dataTable))) {
            while ((line = reader.readLine()) != null) {
                String[] registeredUser = line.split(",");
                registeredUsers.put(registeredUser[0], registeredUser[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
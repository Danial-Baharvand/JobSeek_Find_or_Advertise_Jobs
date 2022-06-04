package classes;

import java.io.*;

import java.util.HashMap;


public class Application {

    public HashMap<String, String> registeredUsers = new HashMap<String, String>();
    private String currentUser;
    final static String dt_registeredUsers = "out/production/src/dataTables/registeredUsers.csv";
    final static String dt_jobSeekers = "out/production/src/dataTables/jobSeekers.csv";
    final static String dt_resumes = "out/production/src/dataTables/resumes.csv";

    public Application() {
        this.readRegisteredUsers();
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
        try (BufferedReader reader = new BufferedReader(new FileReader(dt_registeredUsers))) {
            while ((line = reader.readLine()) != null) {
                String[] registeredUser = line.split(",");
                registeredUsers.put(registeredUser[0], registeredUser[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCurrentUser(String email) {
        this.currentUser = email;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public HashMap<String, String> getRegisteredUsers() {
        return registeredUsers;
    }
}
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
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] registeredUser = line.split(",");
                registeredUsers.putIfAbsent(registeredUser[0], registeredUser[1]);
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

    /**
     * updates the registeredUsers Hashmap by calling the readRegisteredUsers method to read the registeredUsers file
     * @param email is the email to search for in the registeredUsers Hashmap
     * @return true if the email already exists as a Key in the registeredUsers Hashmap, else false
     */
    public boolean isRegisteredUser(String email) {
        this.readRegisteredUsers();
        return this.registeredUsers.containsKey(email);
    }
}
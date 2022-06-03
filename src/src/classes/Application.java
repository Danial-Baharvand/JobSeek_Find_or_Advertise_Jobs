package classes;

import java.io.*;

import java.util.HashMap;


public class Application {

    public HashMap<String, String> registeredUsers = new HashMap<String, String>();
    private String currentUser;
    String dt_registeredUsers = "/Users/jackstoneman/JavaProjects/group-i/out/production/src/dataTables/registeredUsers.csv";
    String dt_jobSeekers = "/Users/jackstoneman/JavaProjects/group-i/out/production/src/dataTables/jobSeekers.csv";

    public Application() {
        this.readRegisteredUsers();
    }

    /**
     * @param email is String that is a User's email
     * @param password is a String that is a User's password
     * adds the email of the logged in User to the currentUser ArrayList
     * @return true if the email and password parameters match a key:value pair in the registeredUsers HashMap
     * else false
     */
    public void createNewJobSeeker(String email, String password, String firstName, String lastName) {
        JobSeeker newJobSeeker = new JobSeeker(email, password, firstName, lastName);
        CSVWriter writer = new CSVWriter();
        String newRegisteredUserDetails = newJobSeeker.getEmail().concat(",").concat(newJobSeeker.getPassword());
        writer.newLine(dt_registeredUsers, newRegisteredUserDetails);
        String newJobSeekerDetails = newJobSeeker.getFirstName().concat(",").concat(newJobSeeker.getLastName()).concat(",").concat(newJobSeeker.getEmail());
        writer.newLine(dt_jobSeekers, newJobSeekerDetails);
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

}
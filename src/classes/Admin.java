package classes;

public class Admin extends User{
    /**
     * Admin accounts have permission to activate and deactivate accounts, create categories and edit their keywords
     * as well as generating statistics
     * @param email of admin
     * @param fullName of admin
     * @param password of admin
     */
    public Admin(String email, String fullName, String password) {
        super(email, fullName, password);
    }


}

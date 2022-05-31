package classes;

public class Runtime {

    public static void main(String[] args) {

        Application testSystem = new Application();

        LoginPage testLoginPage = new LoginPage();

        testSystem.userLogin(testLoginPage.getEmail(), testLoginPage.getPassword());

        System.out.println(testSystem.getCurrentUser());

    }
}
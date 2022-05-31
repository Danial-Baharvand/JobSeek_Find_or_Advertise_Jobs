package classes;

import classes.pages.LoginPage;

public class Runtime {

    public static void main(String[] args) {

        Application testSystem = new Application();

        testSystem.createNewJobSeeker("jack@example.com" ,"abc12", "Jack", "L");
        testSystem.createNewJobSeeker("matthew@example.com" ,"xyz123", "Matthew", "W");
        testSystem.createNewJobSeeker("danial@example.com", "abcxyz", "Danial", "B");

        LoginPage testLoginPage = new LoginPage();

        testSystem.userLogin(testLoginPage.getEmail(), testLoginPage.getPassword());

        //System.out.println(testSystem.getCurrentUser());

    }
}
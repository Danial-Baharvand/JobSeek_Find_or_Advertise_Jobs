package classes;

import java.util.*;

public class User{
    private String userName;
    private String name;
    private String phoneNo;
    private String email;
    private int type;

    public User(String userName, String name, String phoneNo, String email){
        this.userName = userName;
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public User() {
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPhoneNumber(String phoneNo){
        this.phoneNo = phoneNo;
    }

    public String getPhoneNumber(){
        return phoneNo;
    }

    public void setEmailAddress(String email){
        this.email = email;
    }

    public String getEmailAddress(){
        return email;
    }

    public void setType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }
}
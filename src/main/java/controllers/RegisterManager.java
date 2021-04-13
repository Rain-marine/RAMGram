package controllers;

import models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterManager {


    public void makeNewUser(String fullName, String username, String password, String passwordRe, String email, String phoneNumber, String bio, String birthDate)   {
        Date birthday;
        try {
            birthday = new SimpleDateFormat("yyyy/MM/dd").parse(birthDate);
        } catch (ParseException e) {
            return;
        }

        User user = new User(username,fullName,email,password,phoneNumber, bio,birthday);
    }
    public boolean checkUsernameUniqueness (String username){

        return true;
    }

    public void makeNewUser (){

    }

    public boolean isUsernameAvailable(String username) {


    }

    public boolean isEmailAvailable(String email) {

    }

    public boolean isPhoneNumberAvailable(String phoneNumber) {

    }
}

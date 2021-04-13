package controllers;

import models.User;
import repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterManager {
    UserRepository userRepository;

    public void makeNewUser(String fullName, String username, String password, String email, String phoneNumber, String bio, String birthDate)   {
        Date birthday;
        try {
            birthday = new SimpleDateFormat("yyyy/MM/dd").parse(birthDate);
        } catch (ParseException e) {
            return;
        }

        User user = new User(username,fullName,email,password,phoneNumber, bio,birthday);
    }

    public boolean isUsernameAvailable(String username) {
        boolean isAvailable = false;
        User user = userRepository.getByUsername(username);
        if (user == null){
            isAvailable = true;
        }
        return isAvailable;

    }

    public boolean isEmailAvailable(String email) {
        boolean isAvailable = false;
        User user = userRepository.getByEmail(email);
        if (user == null){
            isAvailable = true;
        }
        return isAvailable;

    }

    public boolean isPhoneNumberAvailable(String phoneNumber) {
        boolean isAvailable = false;
        User user = userRepository.getByPhoneNumber(phoneNumber);
        if (user == null){
            isAvailable = true;
        }
        return isAvailable;
    }
}

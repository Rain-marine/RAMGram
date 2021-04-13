package controllers;

import models.User;

public class UserController {
    User user;
    long userId;

    public void makeNewUser(String fullName, String username, String password, String passwordRe, String email, String phoneNumber, String bio, String birthDate) {
        user = new User(fullName,username,password,passwordRe,email,phoneNumber,bio,birthDate);
    }
}
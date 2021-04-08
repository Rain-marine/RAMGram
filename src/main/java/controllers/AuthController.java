package controllers;

import exceptions.InvalidInputException;
import models.User;

public class AuthController {

    public User login(String username, String password) throws InvalidInputException {
        if(!password.equals("1234")) {
            throw new InvalidInputException("Wrong password.");
        }
        return null;
    }
}

package controllers;

import exceptions.InvalidInputException;
import models.User;
import repository.UserRepository;

public class AuthController {
    private UserRepository userRepository;

    public User login(String username, String password) throws InvalidInputException {
        User user = userRepository.getByUsername(username);
        if (user == null){
            throw new InvalidInputException("Username not found");
        }
        else {
            String rightPass = user.getPassword();
            if (!rightPass.equals(password))
                throw new InvalidInputException("Wrong password");
            else
                return user;
        }

    }
}

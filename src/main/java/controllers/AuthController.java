package controllers;

import exceptions.InvalidInputException;
import models.LoggedUser;
import models.User;
import repository.UserRepository;

import java.util.Date;

public class AuthController {
    private final UserRepository userRepository = new UserRepository();

    public User login(String username, String password) throws InvalidInputException {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new InvalidInputException("Username not found");
        } else {
            String rightPass = user.getPassword();
            if (!rightPass.equals(password))
                throw new InvalidInputException("Wrong password");
            else {
                LoggedUser.setLoggedUser(user);
                if (user.isActive()) {
                    userRepository.setLastSeen(user.getId(), new Date());
                }
                return user;

            }

        }

    }
}

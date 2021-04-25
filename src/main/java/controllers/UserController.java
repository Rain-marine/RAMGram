package controllers;

import models.LoggedUser;
import models.User;
import repository.UserRepository;

public class UserController {
    private final UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }

    public void blockUser(User userToBlock) {
        //ToDo
        LoggedUser.getLoggedUser().getBlackList().add(userToBlock);
    }

    public void muteUser(User user) {
    }

    public User getUserByUsername(String usernameToFind) {
        return userRepository.getByUsername(usernameToFind);
    }
}

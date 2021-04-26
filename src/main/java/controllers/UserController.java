package controllers;

import models.LoggedUser;
import models.User;
import repository.UserRepository;

import java.util.Date;

public class UserController {
    private final UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }

    public void blockUser(User userToBlock) {
        //ToDo
        //add to blacklist and remove from all factions
    }

    public void muteUser(User user) {
    }

    public User getUserByUsername(String usernameToFind) {
        return userRepository.getByUsername(usernameToFind);
    }

    public void reportUser(User user) {
    }

    public void ChangeUsername(String newUsername) {
        userRepository.changeUsername(newUsername);
    }

    public void changeBio(String newBio) {
        userRepository.changeBio(newBio);
    }

    public void changeName(String newName) {
        userRepository.changeFullName(newName);
    }

    public void changeBirthday(Date birthday) {
        userRepository.changeBirthdayDate(birthday);
    }

    public void changeEmail(String newEmail) {
        userRepository.changeEmail(newEmail);
    }

    public void changeNumber(String newNumber) {
        userRepository.changePhoneNumber(newNumber);
    }

    public void unblockUser(User user) {
        userRepository.unblock(user);
    }
}

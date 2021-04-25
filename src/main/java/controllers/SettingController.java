package controllers;


import models.LoggedUser;
import models.User;
import repository.UserRepository;

import java.util.Date;


public class SettingController {
    private final UserRepository userRepository;

    public SettingController() {
        this.userRepository = new UserRepository();
    }

    public void logout() {
        userRepository.setLastSeen(LoggedUser.getLoggedUser().getId(), new Date());
        LoggedUser.setLoggedUser(null);
    }

    public void deleteAccount() {
        userRepository.deleteAccount(LoggedUser.getLoggedUser().getId());
        LoggedUser.setLoggedUser(null);
    }

    public boolean doesPasswordExist(String password) {
        return userRepository.getById(LoggedUser.getLoggedUser().getId()).getPassword().equals(password);
//        return userRepository.doesPasswordExist(LoggedUser.getLoggedUser().getId(), password);
    }

    public boolean isAccountPublic(String username) {
        User user = userRepository.getByUsername(username);
        return user.isPublic();
    }

    public void changeAccountVisibility(boolean newVisibility) {
        userRepository.changeAccountVisibility(LoggedUser.getLoggedUser().getId(), newVisibility);
    }

    public void deActiveAccount() {
        userRepository.deactivateAccount(LoggedUser.getLoggedUser().getId());
        LoggedUser.setLoggedUser(null);
    }

    public String getUserLastSeenStatus(String username) {
        User user = userRepository.getByUsername(username);
        return user.getLastSeenStatus();
    }

    public void changeLastSeenStatus(String newStatus) {
        userRepository.changeLastSeenStatus(LoggedUser.getLoggedUser().getId() ,newStatus);
    }

    public void changePassword(String newPassword) {
        userRepository.changePassword(LoggedUser.getLoggedUser().getId(), newPassword);
    }
}

package controllers;


import models.LoggedUser;
import repository.UserRepository;

import java.util.Date;


public class SettingController {
    private final UserRepository userRepository;

    public SettingController() {
        this.userRepository = new UserRepository();
    }

    public void logout() {
        userRepository.setLastSeen(LoggedUser.getLoggedUser().getId(), new Date());
    }

    public void deleteAccount() {
        userRepository.deleteAccount(LoggedUser.getLoggedUser().getId());
    }

    public boolean doesPasswordExist(String password) {
        return userRepository.doesPasswordExist(LoggedUser.getLoggedUser().getId(), password);
    }

    public boolean isAccountPublic(String username) {
        return userRepository.isAccountPublic(username);
    }

    public void changeAccountVisibility(boolean newVisibility) {
        userRepository.changeAccountVisibility(LoggedUser.getLoggedUser().getId(), newVisibility);
    }

    public void deActiveAccount() {
        userRepository.deactivateAccount(LoggedUser.getLoggedUser().getId());
    }

    public String getUserLastSeenStatus(String username) {
        return userRepository.getUserLastSeenStatus(username);
    }

    public void changeLastSeenStatus(String newStatus) {
        userRepository.changeLastSeenStatus(LoggedUser.getLoggedUser().getId() ,newStatus);
    }

    public void changePassword(String newPassword) {
        userRepository.changePassword(LoggedUser.getLoggedUser().getId(), newPassword);
    }
}

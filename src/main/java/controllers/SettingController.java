package controllers;


import models.LoggedUser;
import repository.UserRepository;


public class SettingController {
    private final UserRepository userRepository;

    public SettingController() {
        this.userRepository = new UserRepository();
    }

    public void logout() {
        userRepository.setLastSeen(LoggedUser.getLoggedUser().getUsername(), java.time.LocalDateTime.now());
    }

    public void deleteAccount() {
        userRepository.deleteAccount(LoggedUser.getLoggedUser().getUsername());
    }

    public boolean doesPasswordExist(String password) {
        return userRepository.doesPasswordExist(LoggedUser.getLoggedUser().getUsername(), password);
    }

    public boolean isAccountPublic(String username) {
        return userRepository.isAccountPublic(username);
    }

    public void changeAccountVisibility(boolean newVisibility) {
        userRepository.changeAccountVisibility(LoggedUser.getLoggedUser().getUsername(), newVisibility);
    }

    public void deActiveAccount() {
        userRepository.deActiveAccount(LoggedUser.getLoggedUser().getUsername());
    }

    public String getUserLastSeenStatus(String username) {
        return userRepository.getUserLastSeenStatus(username);
    }

    public void changeLastSeenStatus(String newStatus) {
        userRepository.changeLastSeenStatus(LoggedUser.getLoggedUser().getUsername() ,newStatus);
    }

    public void changePassword(String newPassword) {
        userRepository.changePassword(LoggedUser.getLoggedUser().getUsername(), newPassword);
    }
}

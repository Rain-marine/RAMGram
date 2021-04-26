package controllers;


import models.LoggedUser;
import models.Tweet;
import models.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        userRepository.changeLastSeenStatus(LoggedUser.getLoggedUser().getId(), newStatus);
    }

    public void changePassword(String newPassword) {
        userRepository.changePassword(LoggedUser.getLoggedUser().getId(), newPassword);
    }

    public String lastSeenForLoggedUser(User user) {
        String status = user.getLastSeenStatus();
        if (status.equals("everyone"))
            return (user.getLastSeen().toString());
        else if (status.equals("following")){
            List<User> userFollowing = user.getFollowings();
            long loggedUserId = LoggedUser.getLoggedUser().getId();
            for (User following : userFollowing) {
                if (following.getId() == loggedUserId){
                    return (user.getLastSeen().toString());
                }
            }
        }
        return ("last seen recently");
    }

    public String birthdayForLoggedUser(User user) {
        User.Level status = user.isBirthDayVisible();
        if (status == User.Level.FOLLOWING) {
            long loggedUserId = LoggedUser.getLoggedUser().getId();
            List<User> following = user.getFollowings();
            for (User followed : following) {
                if (followed.getId() == loggedUserId) {
                    return user.getBirthday().toString();
                }
            }
            return "not visible";
        } else if (status == User.Level.ALL) {
            return user.getBirthday().toString();
        }
        else {
            return "not visible";
        }
    }


    public String emailForLoggedUser(User user) {
        User.Level status = user.isEmailVisible();
        if (status == User.Level.FOLLOWING) {
            long loggedUserId = LoggedUser.getLoggedUser().getId();
            List<User> following = user.getFollowings();
            for (User followed : following) {
                if (followed.getId() == loggedUserId) {
                    return user.getEmail();
                }
            }
            return "not visible";
        } else if (status == User.Level.ALL) {
            return user.getEmail();
        }
        else {
            return "not visible";
        }

    }

    public String phoneNumberForLoggedUser(User user) {
        User.Level status = user.isPhoneNumberVisible();
        if (status == User.Level.FOLLOWING) {
            long loggedUserId = LoggedUser.getLoggedUser().getId();
            List<User> following = user.getFollowings();
            for (User followed : following) {
                if (followed.getId() == loggedUserId) {
                    return user.getPhoneNumber();
                }
            }
            return "not visible";
        } else if (status == User.Level.ALL) {
            return user.getPhoneNumber();
        }
        else {
            return "not visible";
        }

    }

    public User.Level getUserNumberStatus(User loggedUser) {
        return loggedUser.isPhoneNumberVisible();
    }

    public User.Level getUserEmailStatus(User loggedUser) {
        return loggedUser.isEmailVisible();
    }

    public User.Level getUserBirthdayStatus(User loggedUser) {
        return loggedUser.isBirthDayVisible();
    }

    public void changeNumberStatus(String newStatus) {
        User.Level status = switch (newStatus) {
            case "following" -> User.Level.FOLLOWING;
            case "everybody" -> User.Level.ALL;
            default -> User.Level.NOONE;
        };
        userRepository.changeNumberStatus(LoggedUser.getLoggedUser().getId(), status);
    }

    public void changeEmailStatus(String newStatus) {
        User.Level status = switch (newStatus) {
            case "following" -> User.Level.FOLLOWING;
            case "everybody" -> User.Level.ALL;
            default -> User.Level.NOONE;
        };
        userRepository.changeEmailStatus(LoggedUser.getLoggedUser().getId(), status);
    }

    public void changeBirthdayStatus(String newStatus) {
        User.Level status = switch (newStatus) {
            case "following" -> User.Level.FOLLOWING;
            case "everybody" -> User.Level.ALL;
            default -> User.Level.NOONE;
        };
        userRepository.changeBirthdayStatus(LoggedUser.getLoggedUser().getId(), status);
    }
}

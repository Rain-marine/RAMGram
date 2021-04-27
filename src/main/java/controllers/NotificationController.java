package controllers;

import models.*;
import repository.NotificationRepository;
import repository.UserRepository;

import java.util.List;


public class NotificationController {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;


    public NotificationController() {
        notificationRepository = new NotificationRepository();
        userRepository = new UserRepository();
    }

    public void sendFollowRequestToUser(User user) {
        User receiver = userRepository.getById(user.getId());
        User loggedUser = userRepository.getById(LoggedUser.getLoggedUser().getId());
        Notification followRequestNotification = new Notification(loggedUser,receiver, NotificationType.FOLLOW_REQ);
        notificationRepository.insert(followRequestNotification);
    }

    public void FollowUser(User user) {
        User loggedUser = userRepository.getById(LoggedUser.getLoggedUser().getId());
        User receiver = userRepository.getById(user.getId());
        Notification followNotification = new Notification(loggedUser,receiver, NotificationType.START_FOLLOW);

        notificationRepository.insert(followNotification);
        notificationRepository.addNewFollower(receiver.getId(), loggedUser.getId());
        notificationRepository.addNewFollowing(loggedUser.getId(), receiver.getId());
    }

    public void unfollowUserWithNotification(User user) {
        User loggedUser = userRepository.getById(LoggedUser.getLoggedUser().getId());
        User receiver = userRepository.getById(user.getId());
        Notification unfollowNotification = new Notification(loggedUser,receiver, NotificationType.UNFOLLOW);

        notificationRepository.insert(unfollowNotification);
        notificationRepository.removeFromFollowings(loggedUser.getId(), receiver.getId());
        notificationRepository.removeFromFollowers(receiver.getId(), loggedUser.getId());

        List<Group> loggedUserGroups = loggedUser.getGroups();
        for (Group group : loggedUserGroups) {
            for (User member : group.getMembers()) {
                if (member.getUsername().equals(receiver.getUsername())) {
                    notificationRepository.removeUserFromGroup(loggedUser.getId(), receiver.getId(), group.getId());
                    break;
                }
            }
        }
    }
    public void unfollowUserWithoutNotification(User user) {
        User loggedUser = userRepository.getById(LoggedUser.getLoggedUser().getId());

        notificationRepository.removeFromFollowings(loggedUser.getId(), user.getId());
        notificationRepository.removeFromFollowers(user.getId(), loggedUser.getId());

        List<Group> loggedUserGroups = loggedUser.getGroups();
        for (Group group : loggedUserGroups) {
            for (User member : group.getMembers()) {
                if (member.getUsername().equals(user.getUsername())) {
                    notificationRepository.removeUserFromGroup(loggedUser.getId(), user.getId(), group.getId());
                    break;
                }
            }
        }
    }
}
package controllers;

import models.User;


public class NotificationController {
    public void sendFollowRequestToUser(User user) {
        //ToDO
        //notif for both sides
    }

    public void FollowUser(User user) {
        //notif for user
        //add logged user to user's followers
        //add user to logged user's followings
    }

    public void unfollowUserWithNotif(User user) {
        //remove user from logged user's following
        //remove logged user from user's followers
        //remove user from all factions of loggeduser
    }
    public void unfollowUserWithoutNotif(User user) {
        //remove user from logged user's following
        //remove logged user from user's followers
        //remove user from all factions of loggeduser
        //notif for user
    }
}

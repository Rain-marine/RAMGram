package repository;

import models.Notification;

public class NotificationRepository {

    public void insert(Notification notification) {

    }

    public void addNewFollower(long userId, long newFollowerId) {
        //add newFollowerId to userId followers list
    }

    public void addNewFollowing(long userId, long newFollowingId) {
        //add newFollowingId to userId following list
    }

    public void removeFromFollowings(long userId, long removedFollowingId) {
        //remove removedFollowingId from userId followings list
    }

    public void removeFromFollowers(long userId, long removedFollowerId) {
        //remove removedFollowerId from userId followers list
    }

    public void removeUserFromGroup(long userId, long removedUser, int groupId) {
        //rove removedUser from userId group that it's ID = groupId
    }
}

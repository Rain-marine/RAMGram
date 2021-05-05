package repository;

import models.Group;
import models.Notification;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class NotificationRepository {

    public void insert(Notification notification) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(notification);
            et.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteNotification(long notificationId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Notification object = em.find(Notification.class, notificationId);
            em.remove(object);
            et.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        }
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
}

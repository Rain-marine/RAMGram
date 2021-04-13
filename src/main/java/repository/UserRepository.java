package repository;

import models.User;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class UserRepository {

    public User getById(Long userId){
        EntityManager em = EntityManagerProvider.getEntityManager();

        try {
            return em.find(User.class, userId);
        } catch (Exception e) {
            return null;
        }
    }

    public void insert(User user){
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(user);
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

    public void setLastSeen(String username, Date now) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, username); // is Ok?
            user.setLastSeen(now);
            em.persist(user);
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

    public void deleteAccount(String username) {

    }

    public boolean doesPasswordExist(String username, String password) {
        return false;
    }

    public boolean isAccountPublic(String username) {
        return false;
    }

    public void changeAccountVisibility(String username, boolean newVisibility) {

    }

    public void deActiveAccount(String username) {

    }

    public String getUserLastSeenStatus(String username) {
        return null;
    }

    public void changeLastSeenStatus(String username, String newStatus) {

    }

    public void changePassword(String username, String newPassword) {

    }

    public User getByUsername(String username) {
        return null;
    }

    public User getByEmail(String email) {
        return null;
    }

    public User getByPhoneNumber(String phoneNumber) {
        return null;
    }
}

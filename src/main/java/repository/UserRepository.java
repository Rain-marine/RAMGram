package repository;

import models.User;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class UserRepository {

    public User getById(Long userId){
        EntityManager entityManager = EntityManagerProvider.getEntityManager();

        try {
            return entityManager.find(User.class, userId);
        } catch (Exception e) {
            return null;
        }
    }

    public void insert(User user) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(user);
            entityTransaction.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void setLastSeen(String username, LocalDateTime now) {

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
}

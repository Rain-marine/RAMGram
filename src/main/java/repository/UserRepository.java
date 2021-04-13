package repository;

import models.User;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

    public User getByUsername(String username) {
        //get username and find the return the User object with that username.
        //if the username doesn't exists, return null
        return null;
    }

    public User getByEmail(String email) {
        //get email and find the return the User object with that email.
        //if the email doesn't exists, return null
        return null;
    }

    public User getByPhoneNumber(String phoneNumber) {
        //get phoneNumber and find the return the User object with that phoneNumber.
        //if the phoneNumber doesn't exists, return null
        return null;
    }
}

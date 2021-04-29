package repository;

import models.Message;
import models.Tweet;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class MessageRepository {


    public List<Message> getFavoriteMessages(long id) {
        //Use the list in User object
        return  null;
    }

    public List<Tweet> getFavoriteTweets(long id) {
        //Same as above
        return null;
    }

    public void insert(Message message) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(message);
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
}

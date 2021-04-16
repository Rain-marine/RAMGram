package repository;

import models.Tweet;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;

public class MessageRepository {
    private final EntityManager em = EntityManagerProvider.getEntityManager();
    private final EntityTransaction et = em.getTransaction();

    public ArrayList<Tweet> getRandomTweets() {
        //get 3 random tweets where user account is public and active
        return null;
    }
}

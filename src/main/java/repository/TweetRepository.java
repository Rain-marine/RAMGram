package repository;

import models.Tweet;
import models.User;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;

public class TweetRepository {
    private final EntityManager em = EntityManagerProvider.getEntityManager();
    private final EntityTransaction et = em.getTransaction();

    public void insert(Tweet tweet) {
        try {
            et.begin();
            em.persist(tweet);
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

    public void like(long userId , long tweetId){
        try {
            et.begin();
            Tweet tweet = em.find(Tweet.class, tweetId);
            tweet.getUsersWhoLiked().add(em.find(User.class, userId));
            em.persist(tweet);
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

    public ArrayList<Tweet> getAllTweets(String username) {
        return null; // order by date desc
    }
}

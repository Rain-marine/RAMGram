package repository;

import models.Tweet;
import models.User;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;

public class TweetRepository {

    public void insert(Tweet tweet){
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
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
        EntityManager em = EntityManagerProvider.getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
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
        return null; // order by date desc // tweet where their parentTweet is null
    }

    public ArrayList<Tweet> getTopTweets(String username) {
        return null;
        // public accounts
        // account not muted/blocked by LoggedUser
        //  account not blocked LoggedUser
        // account not deActive
        // tweet not reported by LoggedUser
    }

    public ArrayList<Tweet> getFollowingTweets(String username) {
        return null;
        //account followed by Logged user
        //account not muted by Logged user
    }
}

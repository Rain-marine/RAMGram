package repository;

import models.Tweet;
import models.User;
import repository.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

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

    public List<Tweet> getAllTweets(long userId) {
        //tweets which: 1- tweet's user is userId  2- are in users retweet list
        return null; // order all by date desc // tweet where their parentTweet is null
    }

    public List<Tweet> getTopTweets(long userId) {
        return null;
        // public accounts
        // account not muted/blocked by LoggedUser
        // account not blocked LoggedUser
        // account not deActive
        // tweet not reported by LoggedUser
    }

    public List<Tweet> getFollowingTweets(long userId) {
        return null;

        //account followed by Logged user
        //account not muted by Logged user
    }

    public void addComment(Tweet parentTweet, Tweet commentTweet) {
        //add comment tweet to comments of parent tweet
    }

    public void increaseRetweetCount(long tweetId) {
        //tweet retweet count++
    }

    public void increaseReportCount(long tweetId) {
        //tweet report count++
    }
}

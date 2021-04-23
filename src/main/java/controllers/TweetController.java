package controllers;

import models.LoggedUser;
import models.Tweet;
import models.User;
import repository.TweetRepository;
import repository.UserRepository;

import java.util.ArrayList;

public class TweetController {

    private UserRepository userRepository;
    private TweetRepository tweetRepository;

    public void addTweet(String text){
        Tweet tweet = new Tweet(LoggedUser.getLoggedUser(),text);
        tweetRepository.insert(tweet);
    }

    public ArrayList<Tweet> getAllTweets() {
        return tweetRepository.getAllTweets(LoggedUser.getLoggedUser().getUsername());
    }

    public ArrayList<Tweet> getTopTweets() {
        return tweetRepository.getTopTweets(LoggedUser.getLoggedUser().getUsername());
    }

    public ArrayList<Tweet> getFollowingTweets() {
        return tweetRepository.getFollowingTweets(LoggedUser.getLoggedUser().getUsername());
    }

    public void saveTweet(long id) {

    }

    public void retweet(Tweet currentTweet) {
    }

    public void reportSpam(Tweet currentTweet) {
    }
}

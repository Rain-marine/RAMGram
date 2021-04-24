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

    public ArrayList<Tweet> getUserAllTweets() {
        return tweetRepository.getAllTweets(LoggedUser.getLoggedUser().getId());
    }

    public ArrayList<Tweet> getTopTweets() {
        //ToDo
        return tweetRepository.getTopTweets(LoggedUser.getLoggedUser().getId());
    }

    public ArrayList<Tweet> getFollowingTweets() {
        //ToDo
//        ArrayList<Tweet> followingTweets = new ArrayList<>();
//        ArrayList<Long> following = userRepository.getFollowing(LoggedUser.getLoggedUser().getId());
//        ArrayList<Long> muted = userRepository.getMuted(LoggedUser.getLoggedUser().getId());
//
//        for (long userId : following) {
//            if (!muted.contains(userId)) {
//                followingTweets.addAll(tweetRepository.getAllTweets(userId));
//            }
//        }
//
//        return followingTweets;
        return tweetRepository.getFollowingTweets(LoggedUser.getLoggedUser().getId());
    }

    public void saveTweet(long tweetId) {
        userRepository.addFavoriteTweet(LoggedUser.getLoggedUser(), tweetId);

    }

    public void retweet(Tweet currentTweet) {
        tweetRepository.increaseRetweetCount(currentTweet.getId());
        userRepository.addRetweet(currentTweet.getId(),LoggedUser.getLoggedUser().getId());
    }

    public void reportSpam(Tweet currentTweet) {
        tweetRepository.increaseReportCount(currentTweet.getId());

    }

    public void addComment(String comment, Tweet parentTweet) {
        Tweet commentTweet = new Tweet(LoggedUser.getLoggedUser(),comment);
        commentTweet.setParentTweet(parentTweet);
        tweetRepository.insert(commentTweet);
        tweetRepository.addComment(parentTweet,commentTweet);
    }
}

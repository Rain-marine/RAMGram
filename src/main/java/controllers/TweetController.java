package controllers;

import models.LoggedUser;
import models.Tweet;
import models.User;
import repository.TweetRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TweetController {

    private UserRepository userRepository;
    private TweetRepository tweetRepository;

    public void addTweet(String text){
        Tweet tweet = new Tweet(LoggedUser.getLoggedUser(),text);
        tweetRepository.insert(tweet);
    }

    public List<Tweet> getUserAllTweets() {
        return tweetRepository.getAllTweets(LoggedUser.getLoggedUser().getId());
    }

    public List<Tweet> getTopTweets() {
        //ToDo
        return tweetRepository.getTopTweets(LoggedUser.getLoggedUser().getId());
    }

    public List<Tweet> getFollowingTweets() {
        //ToDo
        List<Tweet> followingTweets = new ArrayList<>();
        List<Long> following = userRepository.getFollowing(LoggedUser.getLoggedUser().getId());
        List<Long> muted = userRepository.getMuted(LoggedUser.getLoggedUser().getId());

        for (long userId : following) {
            if (!muted.contains(userId)) {
                followingTweets.addAll(tweetRepository.getAllTweets(userId));
            }
        }
        return followingTweets.stream().sorted(Comparator.comparing(Tweet::getTweetDateTime)).
                 collect(Collectors.toList());

        //return tweetRepository.getFollowingTweets(LoggedUser.getLoggedUser().getId());
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

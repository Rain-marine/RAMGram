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

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    public TweetController() {

        userRepository = new UserRepository();
        tweetRepository = new TweetRepository();
    }


    public void addTweet(String text){
        Tweet tweet = new Tweet(LoggedUser.getLoggedUser(),text);
        tweetRepository.insert(tweet);
    }

    public List<Tweet> getUserAllTweets(User user) {
        List<Tweet> userAllTweets = tweetRepository.getAllTweets(user.getId());
        userAllTweets.addAll(user.getRetweetTweets());
        return userAllTweets.stream().sorted(Comparator.comparing(Tweet::getTweetDateTime)).
                collect(Collectors.toList());
    }

    public List<Tweet> getTopTweets() {
        //ToDo
        return tweetRepository.getTopTweets(LoggedUser.getLoggedUser().getId());
    }

    public List<Tweet> getFollowingTweets() {
        List<Tweet> followingTweets = new ArrayList<>();
        User currentUser = userRepository.getById(LoggedUser.getLoggedUser().getId());
        List<User> following = currentUser.getFollowings();
        List<User> muted = currentUser.getMutedUsers();

        for (User user : following) {
            if (muted.stream().noneMatch(it -> it.getId() == user.getId())) {
                followingTweets.addAll(getUserAllTweets(user));
            }
        }
        return followingTweets.stream().sorted(Comparator.comparing(Tweet::getTweetDateTime)).
                 collect(Collectors.toList());
    }

    public void saveTweet(long tweetId) {
        userRepository.addFavoriteTweet(LoggedUser.getLoggedUser().getId(), tweetId);

    }

    public void retweet(Tweet currentTweet) {
        userRepository.addRetweet(currentTweet.getId(),LoggedUser.getLoggedUser().getId());
    }

    public void reportSpam(Tweet currentTweet) {
        tweetRepository.increaseReportCount(currentTweet.getId());
        userRepository.addReportedTweet(currentTweet.getId(), LoggedUser.getLoggedUser().getId());
    }

    public void addComment(String comment, Tweet parentTweet) {
        Tweet commentTweet = new Tweet(LoggedUser.getLoggedUser(),comment);
        commentTweet.setParentTweet(parentTweet);
        tweetRepository.insert(commentTweet);
        tweetRepository.addComment(parentTweet,commentTweet);
    }

}

package controllers;

import models.Tweet;
import models.User;
import repository.TweetRepository;
import repository.UserRepository;

public class TweetController {

    private UserRepository userRepository;
    private TweetRepository tweetRepository;

    public void addTweet(Long userId , String text){
        User user = userRepository.getById(userId);
        Tweet tweet = new Tweet(user,text);
        tweetRepository.insert(tweet);
    }
}

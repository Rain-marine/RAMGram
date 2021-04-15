package controllers;

import models.Tweet;
import models.User;
import repository.MessageRepository;
import repository.UserRepository;

import java.util.ArrayList;

public class ExplorerController {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ExplorerController() {
        messageRepository = new MessageRepository();
        userRepository = new UserRepository();
    }

    public ArrayList<Tweet> getTopTweets() {
        return messageRepository.getRandomTweets();
    }

    public User getUserByUsername(String userToFind) {
        return userRepository.getByUsername(userToFind);
    }
}

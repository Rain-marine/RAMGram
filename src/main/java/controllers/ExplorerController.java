package controllers;

import models.Tweet;
import models.User;
import repository.MessageRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ExplorerController {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ExplorerController() {
        messageRepository = new MessageRepository();
        userRepository = new UserRepository();
    }

    public List<Tweet> getTopTweets() {
        return null;
    }

    public User getUserByUsername(String userToFind) {
        return userRepository.getByUsername(userToFind);
    }
}

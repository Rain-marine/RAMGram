package controllers;

import models.Tweet;
import models.User;
import repository.MessageRepository;

import java.util.ArrayList;

public class ExplorerController {
    private final MessageRepository messageRepository;

    public ExplorerController() {
        messageRepository = new MessageRepository();
    }

    public ArrayList<Tweet> getTopTweets() {
        return messageRepository.getRandomTweets();
    }

    public User getUserByUsername(String userToFind) {
    return null;
    }
}

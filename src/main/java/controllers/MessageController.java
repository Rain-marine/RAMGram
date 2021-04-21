package controllers;

import models.LoggedUser;
import models.Message;
import models.Tweet;
import repository.MessageRepository;

import java.util.ArrayList;

public class MessageController {
    private final MessageRepository messageRepository;

    public MessageController() {
        messageRepository = new MessageRepository();
    }

    public ArrayList<Message> getSavedMessage(){
        return messageRepository.getFavoriteMessages(LoggedUser.getLoggedUser().getId());
    }

    public ArrayList<Message> getPeopleMessage() {
        return null;
    }

    public void insert(Message message) {

    }

    public ArrayList<Tweet> getSavedTweets() {
        return messageRepository.getFavoriteTweets(LoggedUser.getLoggedUser().getId());
    }
}
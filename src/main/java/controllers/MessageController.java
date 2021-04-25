package controllers;

import models.LoggedUser;
import models.Message;
import models.Tweet;
import repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

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

    public boolean canSendMessageToUser(String userToSendMessage) {
        return false;
    }

    public boolean canSendMessageToGroup(String groupToSendMessage) {
        return false;
    }

    public void sendMessage(String message, List<String> users, List<String> groups) {

    }
}
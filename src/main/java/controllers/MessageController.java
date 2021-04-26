package controllers;

import models.*;
import repository.MessageRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class MessageController {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageController() {
        messageRepository = new MessageRepository();
        userRepository = new UserRepository();
    }

    public List<Message> getSavedMessage(){
        return messageRepository.getFavoriteMessages(LoggedUser.getLoggedUser().getId());
    }


    public void insert(Message message) {
        messageRepository.insert(message);
    }

    public List<Tweet> getSavedTweets() {
        return messageRepository.getFavoriteTweets(LoggedUser.getLoggedUser().getId());
    }

    public boolean canSendMessageToUser(String userToSendMessage) {
        User user = userRepository.getByUsername(userToSendMessage);
        if (user == null || !user.isActive())
            return false;
        if (hasFollow(user.getFollowers()))
            return true;
        return isFollower(user.getFollowings());
    }

    private boolean isFollower(List<User> followings) {
        for (User user : followings) {
            if (user.getUsername().equals(LoggedUser.getLoggedUser().getUsername()))
                return true;
        }
        return false;
    }

    private boolean hasFollow(List<User> followers) {
        for (User user : followers) {
            if (user.getUsername().equals(LoggedUser.getLoggedUser().getUsername()))
                return true;
        }
        return false;
    }

    public boolean canSendMessageToGroup(String groupToSendMessage) {
        User user = userRepository.getById(LoggedUser.getLoggedUser().getId());
        for (Group group : user.getGroups()) {
            if (group.getName().equals(groupToSendMessage))
                return true;
        }
        return false;
    }

    public void sendMessage(String message, List<String> users, List<String> groups) {

    }
}
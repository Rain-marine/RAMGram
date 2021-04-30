package controllers;

import models.*;
import repository.ChatRepository;
import repository.MessageRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageController {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public MessageController() {
        messageRepository = new MessageRepository();
        userRepository = new UserRepository();
        chatRepository = new ChatRepository();
    }

    public List<Message> getSavedMessage(){
        User user = userRepository.getById(LoggedUser.getLoggedUser().getId());
        return user.getFavoriteMessages();
    }


    public void insert(Message message) {
        messageRepository.insert(message);
    }

    public List<Tweet> getSavedTweets() {
        User user = userRepository.getById(LoggedUser.getLoggedUser().getId());
        return user.getFavoriteTweets();
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

    public void sendMessage(String message, List<String> users, List<String> groupsToSendMessage) {
        User user = userRepository.getById(LoggedUser.getLoggedUser().getId());
        List<Chat> chats = user.getUserChats();
        List<Group> groups = user.getGroups();
        HashMap<String, Group> groupNameToGroup = extractGroupNameToGroup(groups);

        sendMessageToUsers(message, users, chats);
        sendMessageToGroups(message, groupsToSendMessage, groupNameToGroup, chats);
    }

    private void sendMessageToGroups(String message, List<String> groupsToSendMessage, HashMap<String, Group> groups, List<Chat> chats) {
        for (String groupName : groupsToSendMessage) {
            List<String> users = new ArrayList<>();
            groups.get(groupName).getMembers().forEach(member -> users.add(member.getUsername()));
            sendMessageToUsers(message, users, chats);
        }
    }

    private void sendMessageToUsers(String message, List<String> users, List<Chat> chats) {
        for (String user : users) {
            boolean hasSent = false;
            User loggedUser = userRepository.getById(LoggedUser.getLoggedUser().getId());
            User receiver = userRepository.getByUsername(user);
            Message newMessage = new Message(message, loggedUser, receiver);
            for (Chat chat : chats) {
                if (chat.getUsers().size() == 2 &&
                        (chat.getUsers().get(0).getUsername().equals(user)
                                || chat.getUsers().get(1).getUsername().equals(user))) {
                    chatRepository.addMessageToChat(chat.getId(), newMessage);
                    hasSent = true;
                    break;
                }
            }
            if (!hasSent) {
                Chat newChat = new Chat(new ArrayList<>() {
                    {
                        add(loggedUser);
                        add(receiver);
                    }
                });
                newChat.setMessages(new ArrayList<>() {
                    {
                        add(newMessage);
                    }
                });
                newChat.setHasSeen(false);
                newChat.setUnseenCount(1);
                chatRepository.insert(newChat);
            }
        }
    }

    public void insertSavedMessage(Message message) {
        messageRepository.addMessageToSavedMessage(LoggedUser.getLoggedUser().getId(), message);
    }

    private HashMap<String, Group> extractGroupNameToGroup(List<Group> groups) {
        HashMap<String, Group> groupNameToGroup = new HashMap<>();
        groups.forEach(group -> groupNameToGroup.put(group.getName(), group));

        return groupNameToGroup;
    }
}
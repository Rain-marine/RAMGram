package controllers;

import models.Chat;
import models.LoggedUser;
import models.Message;
import models.User;
import repository.ChatRepository;
import repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChatController {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatController() {
        chatRepository = new ChatRepository();
        userRepository = new UserRepository();
    }

    public List<Chat> getChats() {
        return chatRepository.getAllChats(LoggedUser.getLoggedUser().getId());
    }

    public void seeChat(Chat chat) {
        chatRepository.clearUnSeenCount(chat.getId(), LoggedUser.getLoggedUser().getId());
    }

    public void addMessageToChat(long chatId, String message, User frontUser) {
        Message newMessage = new Message(message,
                userRepository.getById(LoggedUser.getLoggedUser().getId()),
                userRepository.getById(frontUser.getId()));
        chatRepository.addMessageToChat(chatId, newMessage);
    }

    public List<Message> getMessages(Chat chat) {
        Chat freshChat = chatRepository.getById(chat.getId());
        return freshChat.getMessages().stream().sorted(Comparator.comparing(Message::getDate)).collect(Collectors.toList());
    }
}
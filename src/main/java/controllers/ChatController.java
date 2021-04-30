package controllers;

import models.Chat;
import models.LoggedUser;
import models.Message;
import repository.ChatRepository;

import java.util.List;

public class ChatController {
    private final ChatRepository chatRepository;

    public ChatController() {
        chatRepository = new ChatRepository();
    }

    public List<Chat> getChats() {
        return chatRepository.getAllChats(LoggedUser.getLoggedUser().getId());
    }

    public void addMessageToChat(long chatId, Message message) {

    }
}
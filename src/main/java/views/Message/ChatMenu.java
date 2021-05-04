package views.Message;

import controllers.ChatController;
import models.Chat;
import models.LoggedUser;
import models.Message;
import models.User;
import views.Menu;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ChatMenu extends Menu {
    private final Chat chat;
    private final User frontUser;
    private final ChatController chatController;
    private final Menu previousMenu;

    public ChatMenu(Chat chat, Menu previousMenu) {
        this.chat = chat;
        this.frontUser = chat.getUsers().get(0).getUsername().equals(LoggedUser.getLoggedUser().getUsername())
                ? chat.getUsers().get(1)
                : chat.getUsers().get(0);
        this.previousMenu = previousMenu;
        chatController = new ChatController();
    }

    @Override
    public void run() {
        chatController.seeChat(chat);
        List<Message> messages = chat.getMessages().stream().sorted(Comparator.comparing(Message::getDate)).collect(Collectors.toList());
        System.out.println(frontUser.getUsername() + "\t" + frontUser.getLastSeen()); // fix last seen
        showMessages(messages);
        while (true) {
            System.out.println("You are in " + frontUser.getUsername() + " direct!\n type \"new message\" to send message or any key to back");
            String input = scanner.nextLine();
            if (input.equals("new message")){
                getNewMessage();
            } else
                break;
        }
        previousMenu.run();
    }

    private void getNewMessage() {
        System.out.println("Type your message!");
        String message = scanner.nextLine();
        chatController.addMessageToChat(chat.getId(), message, frontUser);
        System.out.println("You : " + message + " Date : " + new Date());
        System.out.println("press any key to continue!");
        scanner.nextLine();
    }

    private void showMessages(List<Message> messages) {
        if (messages.size() == 0) {
            System.out.println("There is no message to show!");
            return;
        }
        for (Message message : messages) {
            String usernameToShow = message.getSender().getUsername().equals(LoggedUser.getLoggedUser().getUsername()) ?
                    "You" : message.getReceiver().getUsername();
            System.out.println(usernameToShow + " : " + message.getText() + "\tDate : " + message.getDate().toString());
        }
    }

    @Override
    public Menu getMenu(int option) {
        return null;
    }
}

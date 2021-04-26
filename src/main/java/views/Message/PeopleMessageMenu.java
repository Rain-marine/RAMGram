package views.Message;

import controllers.ChatController;
import models.Chat;
import models.LoggedUser;
import models.User;
import views.Menu;

import java.util.HashMap;
import java.util.List;

public class PeopleMessageMenu extends Menu{
    private final ChatController chatController;

    public PeopleMessageMenu() {
        chatController = new ChatController();
    }

    @Override
    public void run() {
        System.out.println("**Message With People**");
        List<Chat> chats = chatController.getChats();
        if (chats.size() == 0) {
            System.out.println("YOU HAVE NO CHAT\nyou get back to message menu! enter any key to continue!");
            scanner.nextLine();
            return;
        }
        showChats(chats);
        HashMap<String, Chat> usernameToChat = extractUserFromChat(chats);
        while (true) {
            System.out.println("enter username to see the chat! or enter *back for back to message menu!");
            String input = scanner.nextLine();
            if (input.equals("*back"))
                break;
            if (!usernameToChat.containsKey(input)) {
                System.out.println("no user found!\nenter any key to continue!");
                scanner.nextLine();
            } else
                new ChatMenu(usernameToChat.get(input)).run();
        }
    }

    private HashMap<String, Chat> extractUserFromChat(List<Chat> chats) {
        HashMap<String, Chat> usernameToChat = new HashMap<>();
        for (Chat chat : chats) {
            User userToShow = chat.getUsers().get(0).getUsername().equals(LoggedUser.getLoggedUser().getUsername())
                    ? chat.getUsers().get(1)
                    : chat.getUsers().get(0);
            usernameToChat.put(userToShow.getUsername(), chat);
        }
        return usernameToChat;
    }

    @Override
    public Menu getMenu(int option) {
        return new MessageMenu();
    }

    public void showChats(List<Chat> chats) {
        for (Chat chat : chats) {
            User userToShow = chat.getUsers().get(0).getUsername().equals(LoggedUser.getLoggedUser().getUsername())
                    ? chat.getUsers().get(1)
                    : chat.getUsers().get(0);

            if (chat.isHasSeen())
                System.out.println(userToShow.getUsername());
            else
                System.out.println(userToShow.getUsername() + "\t" + chat.getUnseenCount() + " Unseen Message");
        }
    }
}

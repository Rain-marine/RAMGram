package views.Message;

import models.Chat;
import models.LoggedUser;
import models.Message;
import models.User;
import views.Menu;

import java.util.List;

public class ChatMenu extends Menu {
    private final Chat chat;
    private final User frontUser;
    public ChatMenu(Chat chat) {
        this.chat = chat;
        this.frontUser = chat.getUsers().get(0).getUsername().equals(LoggedUser.getLoggedUser().getUsername())
                ? chat.getUsers().get(1)
                : chat.getUsers().get(0);
    }

    @Override
    public void run() {
        List<Message> messages = chat.getMessages();
        System.out.println(frontUser.getUsername() + "\t" + frontUser.getLastSeen()); // fix last seen

    }

    @Override
    public Menu getMenu(int option) {
        return null;
    }
}

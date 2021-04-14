package views.Message;

import controllers.MessageController;
import models.LoggedUser;
import models.Message;
import models.User;
import views.Menu;
import views.View;

import java.util.ArrayList;

public class PeopleMessageMenu extends Menu implements View<ArrayList<Message>> {
    private final MessageController messageController;

    public PeopleMessageMenu() {
        messageController = new MessageController();
    }

    @Override
    public void run() {
        System.out.println("**Message With Friends**");
        ArrayList<Message> messages = messageController.getPeopleMessage();
        show(messages);
        boolean isValid;
        String input;
        do{
            System.out.println("Please enter a number to see the message or 0 for back to Message Menu");
            input = scanner.nextLine();
            isValid = checkValidation(input, String.valueOf(messages.size()));
        }while(!isValid);


    }

    @Override
    public Menu getMenu(int option) {
        return null;
    }

    @Override
    public boolean checkValidation(String... input) {
        try{
            int number = Integer.parseInt(input[0]);
            if (number >= 0 && number <= Integer.parseInt(input[1]))
                return true;
            System.out.println("You must enter a number between 0 and " + input[1]);
        } catch (Exception e) {
            System.out.println("you haven't entered a number");
        }
        return false;
    }

    @Override
    public void show(ArrayList<Message> messages) {
        if (messages.size() == 0) {
            System.out.println("**YOU HAVE NO MESSAGE TO SHOW");
            return;
        }
        for (int i = 1; i < messages.size() + 1; i++) {
            User userToShow = messages.get(i-1).getSender().getUsername().equals(LoggedUser.getLoggedUser().getUsername())
                    ? messages.get(i-1).getReceiver()
                    : messages.get(i-1).getSender();
            System.out.println(i + " : User : " + userToShow.getUsername());
        }
    }
}

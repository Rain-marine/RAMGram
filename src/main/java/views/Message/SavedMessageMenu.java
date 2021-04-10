package views.Message;

import controllers.MessageController;
import models.LoggedUser;
import models.Message;
import views.Menu;
import views.View;

import java.util.ArrayList;
import java.util.Arrays;

public class SavedMessageMenu extends Menu implements View<ArrayList<Message>> {
    private final MessageController messageController;

    public SavedMessageMenu() {
        messageController = new MessageController();
        options = Arrays.asList("Back", "New Message");
    }


    @Override
    public void run() {
        System.out.println("**Saved Message**");
        ArrayList<Message> savedMessage = messageController.getSavedMessage();
        show(savedMessage);
        boolean isValid;
        String input;
        while (true) {
            do {
                for (int i = 0; i < options.size(); i++) {
                    System.out.println(i + " : " + options.get(i));
                }
                input = scanner.nextLine();
                isValid = checkValidation(input);
            } while (!isValid);

            if (Integer.parseInt(input) == 1)
                break;
            else
                getNewMessage();
        }
        getMenu(1).run();
    }

    private void getNewMessage() {
        String message = scanner.nextLine();
        Message messageToSave = new Message(message, LoggedUser.getLoggedUser(), LoggedUser.getLoggedUser());
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(messageToSave);
        show(messages);
    }

    @Override
    public Menu getMenu(int option) {
        if (option == 1)
            return new MessageMenu();
        else
            return new SavedMessageMenu();
    }

    @Override
    public boolean checkValidation(String... input) {
        try {
            int number = Integer.parseInt(input[0]);
            if (number > 0 && number < 3)
                return true;
            System.out.println("You must enter 1 or 2");
        } catch (Exception e) {
            System.out.println("you haven't entered a number");
        }
        return false;
    }

    @Override
    public void show(ArrayList<Message> messages) {
        if (messages.size() == 0) {
            System.out.println("YOU HAVE NO SAVED MESSAGE, PRESS 1 TO ADD NEW MESSAGE");
            return;
        }
        for (Message message : messages) {
            System.out.println(message.getSender().getUsername() + " : " + message.getText() + "\tDate : " + message.getDate().toString());
        }
    }
}

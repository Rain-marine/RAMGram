package views.Message;

import controllers.MessageController;
import models.LoggedUser;
import models.Message;
import models.Tweet;
import views.Menu;

import java.util.ArrayList;
import java.util.Arrays;

public class SavedMessageMenu extends Menu{
    private final MessageController messageController;

    public SavedMessageMenu() {
        messageController = new MessageController();
        options = Arrays.asList("back", "new message");
    }


    @Override
    public void run() {
        System.out.println("**Saved Message**");
        showOption();
        ArrayList<Message> savedMessage = messageController.getSavedMessage();
        showMessages(savedMessage);
        ArrayList<Tweet> tweets = messageController.getSavedTweets();
        showTweets(tweets);
        while (true) {
            System.out.println("You are in saved message menu, type your request");
            String input = scanner.nextLine();
            if (!options.contains(input)) {
                System.out.println("Invalid input!");
                continue;
            }
            if ("new message".equals(input))
                getNewMessage();
            else
                //ToDo new message menu
                break;
        }
    }



    private void showOption() {
        System.out.println("new message -> add message for yourself");
        System.out.println("back -> back to message menu!");
    }

    private void getNewMessage() {
        System.out.println("type your message");
        String message = scanner.nextLine();
        Message messageToSave = new Message(message, LoggedUser.getLoggedUser(), LoggedUser.getLoggedUser());
        ArrayList<Message> messages = new ArrayList<>(){
            {
                add(messageToSave);
            }
        };
        messageController.insert(messageToSave);
        showMessages(messages);
    }

    @Override
    public Menu getMenu(int option) {
        if (option == 1)
            return new MessageMenu();
        else
            return new SavedMessageMenu();
    }

    public void showMessages(ArrayList<Message> messages) {
        if (messages.size() == 0) {
            System.out.println("YOU HAVE NO SAVED MESSAGE, TYPE new message TO ADD NEW MESSAGE");
            return;
        }
        for (Message message : messages) {
            System.out.println(message.getSender().getUsername() + " : " + message.getText() + "\tDate : " + message.getDate().toString());
        }
    }

    private void showTweets(ArrayList<Tweet> tweets) {
        if (tweets.size() == 0) {
            System.out.println("YOU HAVE NO SAVED TWEETS");
            return;
        }
        for (Tweet tweet : tweets) {
            System.out.println(tweet.getUser().getUsername() + " : " + tweet.getText() + "\tDate : " + tweet.getTweetDateTime().toString());
        }
    }
}

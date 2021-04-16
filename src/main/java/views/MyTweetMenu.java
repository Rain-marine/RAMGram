package views;

import controllers.TweetController;
import models.Tweet;

import java.util.HashMap;
import java.util.List;

public class MyTweetMenu extends Menu {
    private final TweetController tweetController;
    private final HashMap<String, Integer> commands;

    public MyTweetMenu() {
        tweetController = new TweetController();
        commands = new HashMap<>() {
            {
                put("next", 1);
                put("before", 2);
                put("save", 3);
                put("forward", 4);
                put("add comment", 5);
                put("comment", 6);
                put("back", 7);
            }
        };
    }

    @Override
    public void run() {
        List<Tweet> myTweets = tweetController.getAllTweets();
        if (myTweets.size() == 0) {
            System.out.println("You have no tweet to show!");
            new PersonalPageMenu().run();
            return;
        }
        Tweet currentTweet = myTweets.get(0);
        showCommands();
        while (true) {
            System.out.println("Date : " + currentTweet.getTweetDateTime());
            System.out.println(currentTweet.getUser().getUsername() + " : " +
                    currentTweet.getText() + "\n" +
                    currentTweet.getUsersWhoLiked().size() + " people liked");
            Show:
            while (true) {
                String input = scanner.nextLine();
                if (commands.containsKey(input)) {
                    switch (commands.get(input)) {
                        case 1:
                            if (myTweets.indexOf(currentTweet) == myTweets.size() - 1) {
                                System.out.println("There is no tweet to show");
                                continue;
                            } else {
                                currentTweet = myTweets.get(myTweets.indexOf(currentTweet) + 1);
                                break Show;
                            }
                        case 2:
                            if (myTweets.indexOf(currentTweet) == 0) {
                                System.out.println("There is no tweet to show");
                                continue;
                            } else {
                                currentTweet = myTweets.get(myTweets.indexOf(currentTweet) - 1);
                                break Show;
                            }
                        case 3:
                            tweetController.saveTweet(currentTweet.getId());
                            System.out.println("Tweet Saved!");
                        case 4:
                            forwardTweet(currentTweet);
                        case 5:
                            addNewComment(currentTweet);
                        case 6:
                            if (currentTweet.getComments().size() == 0) {
                                System.out.println("No Comment to show");
                                continue;
                            } else {
                                myTweets = currentTweet.getComments();
                                break Show;
                            }
                        case 7:
                            if (currentTweet.getParentTweet() == null) {
                                new PersonalPageMenu().run();
                                return;
                            } else {
                                currentTweet = currentTweet.getParentTweet();
                                myTweets = currentTweet.getParentTweet() == null ? tweetController.getAllTweets() : currentTweet.getParentTweet().getComments();
                                break Show;
                            }
                    }
                }
            }
        }
    }

    private void addNewComment(Tweet tweet) {

    }

    private void forwardTweet(Tweet tweet) {

    }

    private void showCommands() {
        System.out.println("next -> next tweet or comment");
        System.out.println("before -> previous tweet or comment");
        System.out.println("save -> add tweet to your favorite");
        System.out.println("forward -> send tweet for another user");
        System.out.println("add comment -> add comment");
        System.out.println("comment -> show comments");
        System.out.println("back -> back to previous step");
    }

    @Override
    public Menu getMenu(int option) {
        return null;
    }

    @Override
    public boolean checkValidation(String... input) {
        return false;
    }
}

package views;

import controllers.TweetController;
import controllers.UserController;
import models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TimelineMenu extends Menu {
    private final TweetController tweetController;
    private final UserController userController;

    public TimelineMenu() {
        this.userController = new UserController();
        this.tweetController = new TweetController();
    }

    @Override
    public void run() {
        System.out.println("this is your Time Line page. here you see your followings' tweet. " +
                "press enter to continue or enter 0 to go back to MainMenu");
        String input = scanner.nextLine();
        if (input.equals("0"))
            getMenu(0).run();
        else
            getMenu(1).run();
    }

    @Override
    public Menu getMenu(int option) {
        if(option == 0)
            return new MainMenu();
        else {
            List<Tweet> listOfTweets = tweetController.getFollowingTweets();
            return new TweetMenu(listOfTweets, new TimelineMenu());
        }
    }

    @Override
    public boolean checkValidation(String... input) {
        return false;
    }
}

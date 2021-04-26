package views;

import controllers.TweetController;
import models.LoggedUser;
import models.Tweet;
import models.User;
import views.profiles.FollowingProfile;

import java.util.Arrays;
import java.util.List;

public class PersonalPageMenu extends Menu {
    private final TweetController tweetController;

    public PersonalPageMenu() {
        options = Arrays.asList("New Tweet", "Your Tweets", "Edit", "Factions", "Information", "Notification", "Back");
        tweetController = new TweetController();
    }

    @Override
    public void run() {
        System.out.println("**Personal Page**");
        boolean isValid;
        String input;
        do {
            for (int i = 1; i < options.size() + 1; i++) {
                System.out.println(i + " : " + options.get(i-1));
            }
            input = scanner.nextLine();
            isValid = checkValidation(input);
        }while(!isValid);
        int inputInt = Integer.parseInt(input);
        switch (inputInt) {
            case 1 -> writeNewTweet();
            case 2 -> showMyTweets();
            case 3 -> editPage();
            case 4 -> showMyLists();
            case 5 -> showMyInfo();
            case 6 -> showNotifications();
            default -> getMenu(0).run();
        }
    }

    private void showNotifications() {
        //ToDo
        //new notification menu

    }

    private void showMyInfo() {
        User user = LoggedUser.getLoggedUser();
        System.out.println("Username: " + user.getUsername() +"\nName: "+user.getFullName()
        +"\nEmail: "+user.getEmail()
        +"\nBirthday: " + user.getBirthday().toString()
        +"\nPhone Number: "+user.getPhoneNumber()
        +"\nBio: " +user.getBio());
        System.out.println("press enter to go back");
        scanner.nextLine();
        run();

    }

    private void showMyLists() {
        //ToDo
        //new Factions class

        System.out.println("press enter to go back");
        scanner.nextLine();
        run();

    }

    private void editPage() {
        new EditInfo().run();
    }

    private void showMyTweets() {
        new MyTweetMenu().run();
    }


    private void writeNewTweet() {
        System.out.println("Write your Tweet!");
        String tweet = scanner.nextLine();
        System.out.println("Are you sure?(Y/N)");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Y")){
            tweetController.addTweet(tweet);
            System.out.println("Tweet Published!.\nPress any key to continue!");
        } else {
            System.out.println("Tweet Canceled!.\nPress any key to continue!");
        }
        scanner.nextLine();
        run();
    }

    @Override
    public Menu getMenu(int option) {
        if (option == 0)
            return new MainMenu();
        return null;
    }

    @Override
    public boolean checkValidation(String... input) {
        try{
            int inputInt = Integer.parseInt(input[0]);
            if (inputInt > 0 && inputInt < 8) {
                return true;
            }
            System.out.println("input must be between 1 and 7");
        } catch (Exception e) {
            System.out.println("You haven't entered a number!");
        }
        return false;
    }
}

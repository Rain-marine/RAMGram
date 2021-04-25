package views;

import controllers.ExplorerController;
import models.Tweet;
import models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExplorerMenu extends Menu {
    public ExplorerMenu() {
        options = Arrays.asList("Search", "Interested tweets", "Back");
    }

    private ExplorerController explorerController = new ExplorerController();


    @Override
    public void run() {
        System.out.println("**Explorer**");
        boolean isValid;
        String input;

        do {
            for (int i = 1; i < options.size() + 1; i++) {
                System.out.println(i + " : " + options.get(i));
            }
            input = scanner.nextLine();
            isValid = checkValidation(input);
        } while (!isValid);

        int intInput = Integer.parseInt(input);
        if (intInput == 1)
            search();
        else if (intInput == 2)
            showTopTweets();
        else
            getMenu(3).run();
    }

    private void showTopTweets() {
        TweetMenu tweetMenu = new TweetMenu(explorerController.getTopTweets(),getMenu(0));
        tweetMenu.run();
    }

    private void search() {
        System.out.println("type username you want to find or type" + ConsoleColors.PURPLE_BOLD + "*back*" + ConsoleColors.RESET
                + "to go back to explorer page");
        String userToFind = scanner.nextLine();
        if (userToFind.equals("*back*")) {
            getMenu(0).run();
        }
        else {
            User user = explorerController.getUserByUsername(userToFind);
            if (user == null) {
                System.out.println("username not found");
                run();
            } else {
                new ProfilePage(user).run();
            }
        }
    }

    @Override
    public Menu getMenu(int option) {
        if (option == 0)
            return new ExplorerMenu();
        return new MainMenu();
    }

    @Override
    public boolean checkValidation(String... input) {
        try {
            int inputInt = Integer.parseInt(input[0]);
            if (inputInt == 1 || inputInt == 2) {
                return true;
            }
            System.out.println("input should be either 1 or 2");
        } catch (Exception e) {
            System.out.println("You haven't entered a number!");
        }
        return false;
    }
}

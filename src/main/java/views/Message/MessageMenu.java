package views.Message;

import views.MainMenu;
import views.Menu;

import java.util.Arrays;

public class MessageMenu extends Menu {

    public MessageMenu() {
        options = Arrays.asList("SavedMessage", "PeopleMessage", "Back");
    }

    @Override
    public void run() {
        System.out.println("**Message Menu**");
        boolean isValid;
        String input = "";
        do {
            System.out.println("Which messages do you wanna see?!");
            for (int i = 0; i < options.size(); i++) {
                System.out.println(i + " : " + options.get(i));
            }
            input = scanner.nextLine();
            isValid = checkValidation(input);
        } while (!isValid);

        getMenu(Integer.parseInt(input)).run();
    }

    @Override
    public Menu getMenu(int option) {
        switch (option) {
            case 1:
                return new SavedMessageMenu();
            case 2:
                return new PeopleMessageMenu();
            case 3:
                 return new MainMenu();
            default:
                return new MessageMenu();
        }
    }

    @Override
    public boolean checkValidation(String... input) {
        try {
            int number = Integer.parseInt(input[0]);
            if (number > 0 && number < 4)
                return true;
            System.out.println("You must enter 1 or 2");
        } catch (Exception e) {
            System.out.println("you haven't entered a number");
        }
        return false;
    }
}

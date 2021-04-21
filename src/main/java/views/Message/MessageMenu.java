package views.Message;

import views.MainMenu;
import views.Menu;

import java.util.Arrays;

public class MessageMenu extends Menu {

    public MessageMenu() {
        options = Arrays.asList("saved message", "people message", "back");
    }

    @Override
    public void run() {
        System.out.println("**Message Menu**");
        Input:
        while (true) {
            System.out.println("You are in message menu, type your request");
            String input = scanner.nextLine();
            if (!options.contains(input)) {
                System.out.println("unknown input!");
                continue;
            }
            switch(input) {
                case "saved message" -> getMenu(1).run();
                case "people message" -> getMenu(2).run();
                default -> {
                    break Input;
                }
            }
        }
    }

    @Override
    public Menu getMenu(int option) {
        return switch (option) {
            case 1 -> new SavedMessageMenu();
            case 2 -> new PeopleMessageMenu();
            case 3 -> new MainMenu();
            default -> new MessageMenu();
        };
    }

    @Override
    public boolean checkValidation(String... input) {
        return false;
    }
}

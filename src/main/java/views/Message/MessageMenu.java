package views.Message;

import views.MainMenu;
import views.Menu;

import java.util.Arrays;

public class MessageMenu extends Menu {

    public MessageMenu() {
        options = Arrays.asList("saved messages", "people messages", "back");
    }

    @Override
    public void run() {
        System.out.println("**Message Menu**");
        Input:
        while (true) {
            System.out.println("You are in message menu, type your request");
            for (int i = 1; i < options.size() + 1; i++) {
                System.out.println(options.get(i - 1));
            }
            String input = scanner.nextLine();
            if (!options.contains(input)) {
                System.out.println("Invalid input!");
                continue;
            }
            switch (input) {
                case "saved messages" -> getMenu(1).run();
                case "people messages" -> getMenu(2).run();
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

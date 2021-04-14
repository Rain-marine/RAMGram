package views.Welcome;

import controllers.AuthController;
import exceptions.InvalidInputException;
import views.MainMenu;
import views.Menu;

import java.util.concurrent.TimeUnit;

public class LoginMenu extends Menu {


    private final AuthController authController;

    public LoginMenu() {
        authController = new AuthController();
    }

    public void run() {
        System.out.println("Hi! your in login menu. \n Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        try {
            long loggedInId = authController.login(username, password);

        } catch (InvalidInputException e) {
            System.err.println(e.getMessage());
            System.out.println("press enter to return to main menu");
            scanner.nextLine();
            getMenu(0).run();
        }
    }

    @Override
    public Menu getMenu(int option) {
        switch (option){
            case 1 -> { return new MainMenu(); }
            default -> { return new WelcomeMenu(); }

        }

    }

    @Override
    public boolean checkValidation(String... input) {
        return false;
    }
}
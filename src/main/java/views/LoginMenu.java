package views;

import controllers.AuthController;
import exceptions.InvalidInputException;
import models.User;

public class LoginMenu extends Menu {


    private AuthController authController;

    public LoginMenu() {
        authController = new AuthController();
    }

    public void run() {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        try {
            User user = authController.login(username, password);
        } catch (InvalidInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Menu getMenu(int option) {
        return new WelcomeMenu(); // Todo: return next menu (after the user logs in)
    }
}
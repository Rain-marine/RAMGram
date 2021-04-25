package views.Welcome;

import controllers.AuthController;
import exceptions.InvalidInputException;
import models.LoggedUser;
import models.User;
import views.MainMenu;
import views.Menu;
import views.profiles.FollowingProfile;

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
        int option;
        try {
            User user = authController.login(username, password);
            LoggedUser.setLoggedUser(user);
            System.out.println("login successful. press enter to continue");
            scanner.nextLine();
            getMenu(1).run();

        }
        catch (InvalidInputException e) {
            System.err.println(e.getMessage());
            System.out.println("press enter to return to welcome menu");
            scanner.nextLine();
            getMenu(0).run();

        }
    }

    @Override
    public Menu getMenu(int option) {
        if (option == 1) {
            return new MainMenu();
        } else {
            return new WelcomeMenu();
        }

    }

    @Override
    public boolean checkValidation(String... input) {
        return false;
    }
}
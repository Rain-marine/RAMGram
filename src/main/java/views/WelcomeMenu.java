package views;

import java.util.Arrays;
import java.util.List;

public class WelcomeMenu extends Menu {

    public WelcomeMenu() {
        options = Arrays.asList("Login", "Register");
    }

    @Override
    public Menu getMenu(int option) {
        switch (option) {
            case 1:
                return new LoginMenu();
            case 2:
                return new RegisterMenu();
            default:
                return null;
        }
    }
}
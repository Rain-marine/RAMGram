package views.Setting;

import controllers.SettingController;
import views.MainMenu;
import views.Menu;
import views.Welcome.WelcomeMenu;

import java.util.Arrays;

public class SettingMenu extends Menu {
    private final SettingController settingsController;
    public SettingMenu() {
        settingsController = new SettingController();
        options = Arrays.asList("Privacy & Security", "Delete Account", "Log out", "Back");
    }

    @Override
    public void run() {
        System.out.println("**Setting**");
        boolean isValid;
        String input;
        do{
            for (int i = 1; i < options.size() + 1; i++) {
                System.out.println(i + " : " + options.get(i - 1));
            }
            input = scanner.nextLine();
            isValid = checkValidation(input);
        } while (!isValid);
        int inputInt = Integer.parseInt(input);
        switch (inputInt) {
            case 1 -> getMenu(1).run();
            case 2 -> deleteAccount();
            case 3 -> logout();
            default -> getMenu(4).run();
        }
    }

    private void logout() {
        System.out.println("Are you sure?!(Y/N)");
        String input = scanner.nextLine();
        if (input.equals("Y")) {
            settingsController.logout();
            getMenu(3).run();
            return;
        }
        if (input.equals("N")){
            run();
        } else
            logout();
    }

    private void deleteAccount() {
        System.out.println("Are you sure?!(Y/N)");
        String input = scanner.nextLine();
        if (input.equals("Y")) {
            settingsController.deleteAccount();
            getMenu(3).run();
            return;
        }
        if (input.equals("N")){
            run();
        } else
            deleteAccount();
    }

    @Override
    public Menu getMenu(int option) {
        if (option == 1)
            return new PrivacySettingMenu();
        if (option == 3)
            return new WelcomeMenu();
        return new MainMenu();
    }

    @Override
    public boolean checkValidation(String... input) {
        try {
            int inputInt = Integer.parseInt(input[0]);
            if (inputInt > 0 && inputInt < 5) {
                return true;
            }
            System.out.println("input must be between 1 and 4");
        } catch (Exception e) {
            System.out.println("You haven't entered a number!");
        }
        return false;
    }
}

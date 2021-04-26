package views.Setting;

import controllers.SettingController;
import models.LoggedUser;
import views.Menu;
import views.Welcome.WelcomeMenu;

import java.util.ArrayList;
import java.util.Arrays;

public class PrivacySettingMenu extends Menu {
    private final SettingController settingController;

    public PrivacySettingMenu() {
        settingController = new SettingController();
        options = Arrays.asList("private/public", "lastSeen/online", "activation", "change password", "change personal info", "back");
    }

    @Override
    public void run() {
        System.out.println("**Privacy & Security**");
        showOption();
        Input:
        while (true) {
            System.out.println("You are in privacy & security menu, type your request");
            String input = scanner.nextLine();
            if (!options.contains(input)) {
                System.out.println("unknown input!");
                continue;
            }
            switch (input) {
                case "private/public" -> privatePublicChecker();
                case "lastSeen/online" -> lastSeenChecker();
                case "activation" -> activationChecker();
                case "change password" -> changePassword();
                default -> {
                    break Input;
                }
            }
        }
    }

    private void changePassword() {
        System.out.println("Enter your password! if You wanna back, just enter");
        boolean isValid = false;
        while (!isValid) {
            String input = scanner.nextLine();
            if (input.equals(""))
                return;
            isValid = settingController.doesPasswordExist(input);
            if (!isValid)
                System.out.println("Password is incorrect! Enter again!");
        }

        System.out.println("Enter your new password!");
        String newPassword = scanner.nextLine();
        settingController.changePassword(newPassword);
        System.out.println("Password changed!");
        System.out.println("press any key to continue");
        scanner.nextLine();
    }

    private void activationChecker() {
        System.out.println("Do you wanna deActive your account?(Y/N)\nYour account will be ghosted until you active it again!");
        String input = scanner.nextLine();
        if (input.equals("Y")) {
            settingController.deActiveAccount();
            new WelcomeMenu().run();
            return;
        }
        System.out.println("Account is still active and you get back to privacy setting!");
        System.out.println("press any key to continue");
        scanner.nextLine();
    }

    private void lastSeenChecker() {
        ArrayList<String> status = new ArrayList<>() {
            {
                add("nobody");
                add("everybody");
                add("following");
            }
        };

        String lastSeenStatus = settingController.getUserLastSeenStatus(LoggedUser.getLoggedUser().getUsername());
        System.out.println("Your lastSeen is visible for " + lastSeenStatus);
        System.out.println("Do you wanna change your status?(type the status[nobody,everybody,following]) or any character for back");
        String input = scanner.nextLine();
        if (input.equals(lastSeenStatus) || !status.contains(lastSeenStatus))
            System.out.println("Nothing changed! You get back to privacy setting!");
        else {
            settingController.changeLastSeenStatus(input);
            System.out.println("Now your lastSeen is visible for " + input);
        }
        System.out.println("press any key to continue");
        scanner.nextLine();
    }

    private void privatePublicChecker() {
        boolean isPublic = settingController.isAccountPublic(LoggedUser.getLoggedUser().getUsername());
        if (isPublic)
            System.out.println("Your Account is Public! change to Private?(Y/N)");
        else
            System.out.println("Your Account is Private! change to Public?(Y/N)");
        String input = scanner.nextLine();
        if (input.equals("Y")) {
            settingController.changeAccountVisibility(!isPublic);
            System.out.println("Account visibility changed!");
        } else
            System.out.println("No change! \nYou get back to privacy setting!");
        System.out.println("press enter to continue");
        scanner.nextLine();
    }

    private void showOption() {
        System.out.println("private/public -> change account visibility!\n" +
                "lastSeen/online -> change your last seen status!\n" +
                "activation -> deActive your account!\n" +
                "change password -> change your account password!\n" +
                "change personal info -> change information like fullName/email/...\n" +
                "back -> back to setting");
    }

    @Override
    public Menu getMenu(int option) {
        return new SettingMenu();
    }
}

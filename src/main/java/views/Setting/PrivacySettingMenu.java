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
        options = Arrays.asList("private & public", "last seen & online", "activation", "change password", "back");
    }

    @Override
    public void run() {
        System.out.println("**Privacy & Security**");
        boolean isValid;
        String input;
        do{
            for (int i = 1; i < options.size() + 1; i++) {
                System.out.println(i + " : " + options.get(i - 1));
            }
            input = scanner.nextLine();
            isValid = checkValidation(input);
        }while(!isValid);

        int inputInt = Integer.parseInt(input);
        switch (inputInt) {
            case 1 -> privatePublicChecker();
            case 2 -> lastSeenChecker();
            case 3 -> activationChecker();
            case 4 -> changePassword();
            default -> getMenu(5).run();
        }
    }

    private void changePassword() {
        System.out.println("Enter your password! if You wanna back, Enter 'B' ");
        boolean isValid;
        String input;
        do {
            input = scanner.nextLine();
            if (input.equals("B")) {
                run();
                return;
            }
            isValid = settingController.doesPasswordExist(input);
            if (!isValid)
                System.out.println("Password is incorrect! Enter again!");
        } while (!isValid);

        System.out.println("Enter your new password!");
        input = scanner.nextLine();
        settingController.checkPassword(input);
        System.out.println("Password changed!");
        run();
    }

    private void activationChecker() {
        System.out.println("Do you wanna deActive your account?(Y/N)\nYour account will be ghosted until you active it again!");
        String input = scanner.nextLine();
        if (input.equals("Y")) {
            settingController.deActiveAccount();
            new WelcomeMenu().run();
        } else {
            System.out.println("Account is still active and you get back to privacy setting!");
            run();
        }
    }

    private void lastSeenChecker() {
        ArrayList<String> status = new ArrayList<>();
        status.add("nobody");
        status.add("everybody");
        status.add("following");

        String lastSeenStatus = settingController.getUserLastSeenStatus(LoggedUser.getLoggedUser().getUsername());
        System.out.println("Your lastSeen is visible for " + lastSeenStatus);
        System.out.println("Do you wanna change your status?(type the status) or any character for back");
        int printedStatus = 0;
        for (String state : status) {
            if (state.equals(lastSeenStatus))
                continue;
            System.out.println(printedStatus + 1 + " : " + state);
            printedStatus++;
        }

        String input = scanner.nextLine();
        if (input.equals(lastSeenStatus) || !status.contains(lastSeenStatus)) {
            System.out.println("Nothing changed! You get back to privacy setting!");
        } else {
            settingController.changeLastSeenStatus(input);
            System.out.println("Now your lastSeen is visible for " + input);
        }
        run();
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
        } else {
            System.out.println("No change! \n You get back to privacy setting!");
            run();
        }
    }

    @Override
    public Menu getMenu(int option) {
        return new SettingMenu();
    }

    @Override
    public boolean checkValidation(String... input) {
        try{
            int inputInt = Integer.parseInt(input[0]);
            if (inputInt > 0 && inputInt < 6) {
                return true;
            }
            System.out.println("input must be between 1 and 5");
        } catch (Exception e) {
            System.out.println("You haven't entered a number!");
        }
        return false;
    }
}

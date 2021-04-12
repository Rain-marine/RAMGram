package views;

import controllers.SettingController;

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
        do{
            input = scanner.nextLine();
            if (input.equals("B")) {
                run();
                return;
            }
            isValid = settingController.doesPasswordExist(input);
            if (!isValid)
                System.out.println("Password is incorrect! Enter again!");
        }while (!isValid);

    }

    private void activationChecker() {

    }

    private void lastSeenChecker() {
    }

    private void privatePublicChecker() {

    }

    @Override
    public Menu getMenu(int option) {
        return null;
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

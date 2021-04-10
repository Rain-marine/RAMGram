package views;

import java.util.Arrays;

public class WelcomeMenu extends Menu {

    public WelcomeMenu() {
        options = Arrays.asList("LoginManager", "RegisterManager");
    }

    @Override
    public void run() {
        System.out.println("Hi. choose what you want to do");
        String input = "";
        boolean isValid = true;
        do {
            for (int i = 0; i < options.size(); i++) {
                System.out.println(i + " :" + options.get(i));
            }
            input = scanner.nextLine();
            isValid = checkValidation(input);
        } while (!isValid);
        int inputInt = Integer.parseInt(input);
        getMenu(inputInt).run();
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

    @Override
    public boolean checkValidation(String... input) {
        try {
            int inputInt = Integer.parseInt(input[0]);
            if (inputInt == 1 || inputInt == 2) {
                return true;
            }
            System.out.println("input should be either 1 or 2");
        } catch (Exception e) {
            System.out.println("You haven't entered a number!");
        }
        return false;
    }


}
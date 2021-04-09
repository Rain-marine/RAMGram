package views;

import controllers.RegisterManager;

public class RegisterMenu extends Menu {
    private final RegisterManager registerManager;

    public RegisterMenu() {
        this.registerManager = new RegisterManager();
    }

    @Override
    public void run() {
        boolean registerVerified = true;
        do {
            System.out.println("*Enter your full name:");
            String fullName = scanner.nextLine();
            System.out.println("*Enter your username:");
            String username = scanner.nextLine();
            System.out.println("*Enter your password:");
            String password = scanner.nextLine();
            System.out.println("Enter your birth date in yyyy/mm/dd format:");
            String birthDate = scanner.nextLine();
            System.out.println("*Enter your email:");
            String email = scanner.nextLine();
            System.out.println("Enter your phone number:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter your Bio:");
            String Bio = scanner.nextLine();

            registerVerified = checkValidation(username);
        } while (!registerVerified);

        getMenu(1).run();
    }

    @Override
    public Menu getMenu(int option) {
        return new WelcomeMenu();
    }

    @Override
    public boolean checkValidation(String... input) {
        if (input[0].equals("\n") || input[1].equals("\n") )


        return registerManager.checkUsernameUniqueness(input[0]);
    }


}
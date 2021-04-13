package views.Welcome;

import controllers.RegisterManager;
import controllers.UserController;
import views.Menu;
import views.Welcome.WelcomeMenu;

public class RegisterMenu extends Menu {
    private final RegisterManager registerManager;
    private final UserController userController;
    public RegisterMenu() {
        this.registerManager = new RegisterManager();
        this.userController = new UserController();
    }

    @Override
    public void run() {
        System.out.println("Hi! to register complete this form. fields with * are necessary");
        boolean registerSuccessful = true;
        do {
            System.out.println("*Enter your full name:");
            String fullName = scanner.nextLine();
            System.out.println("*Enter your username:");
            String username = scanner.nextLine();
            System.out.println("*Enter your password:");
            String password = scanner.nextLine();
            System.out.println("*re-Enter your password:");
            String passwordRe = scanner.nextLine();
            System.out.println("*Enter your email:");
            String email = scanner.nextLine();
            System.out.println("Enter your phone number:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter your Bio:");
            String bio = scanner.nextLine();
            System.out.println("Enter your birth date in yyyy/mm/dd format:");
            String birthDate = scanner.nextLine();
            registerSuccessful = checkRequiredInputs(fullName,username,password,passwordRe,email,phoneNumber,bio,birthDate);

            if(registerSuccessful){
                System.out.println("register successful!");
                userController.makeNewUser(fullName,username,password,passwordRe,email,phoneNumber,bio,birthDate);
            }



        } while (!registerSuccessful);

        getMenu(1).run();
    }

    public boolean checkRequiredInputs(String fullName,String username ,String password,String passwordRe , String email ,String phoneNumber , String bio , String birthDate){
        if (fullName.equals("")){
            System.out.println("you must enter your fullName");
            return false;
        }
        if (username.equals("")){
            System.out.println("you must enter your username");
            return false;
        }
        if (password.equals("")){
            System.out.println("you must enter your password");
            return false;
        }
        if (passwordRe.equals("")){
            System.out.println("you must Re-enter your password");
            return false;
        }
        if (email.equals("")){
            System.out.println("you must enter your email");
            return false;
        }

        if (!password.equals(passwordRe)){
            System.out.println("passwords don't match");
            return false;
        }

        if (!registerManager.isUsernameAvailable(username)){
            System.out.println("username exists. please enter another username");
            return false;
        }

        if(!email.contains("@") || !email.contains(".")){
            System.out.println("invalid email address");
            return false;
        }

        if (!registerManager.isEmailAvailable(email)){
            System.out.println("email exists");
            return false;
        }
        if (!(phoneNumber.equals(""))){
            if(!registerManager.isPhoneNumberAvailable(phoneNumber)){
                System.out.println("phone number exists");
                return false;
            }
        }
        return true;

    }
    @Override
    public Menu getMenu(int option) {
        return new WelcomeMenu();
    }

    @Override
    public boolean checkValidation(String... input) {
        ///if (input[0].equals("\n") || input[1].equals("\n") )


        return registerManager.checkUsernameUniqueness(input[0]);
    }


}
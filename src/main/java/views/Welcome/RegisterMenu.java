package views.Welcome;

import controllers.RegisterManager;
import views.Menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegisterMenu extends Menu {
    private final RegisterManager registerManager;

    public RegisterMenu() {
        this.registerManager = new RegisterManager();
    }

    @Override
    public void run() {
        System.out.println("Hi! to register complete this form. fields with * are necessary ");
        boolean registerSuccessful;
        do {
            System.out.println("if you want to go back to Welcome Menu enter 1, enter anything else to continue ");
            String firstInput = scanner.nextLine();
            if (firstInput.equals("1")){
                getMenu(1).run();
            }
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
            System.out.println("Enter your birth date in yyyy/MM/dd format:");
            String birthDate = scanner.nextLine();
            registerSuccessful = checkRequiredInputs(fullName,username,password,passwordRe,email,phoneNumber,bio,birthDate);

            if(registerSuccessful){
                System.out.println("register successful!");
                registerManager.makeNewUser(fullName,username,password,email,phoneNumber,bio,birthDate);
            }

        } while (!registerSuccessful);
        System.out.println("press enter to continue");
        scanner.nextLine();
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
        if(!birthDate.equals("")) {
            try {
                new SimpleDateFormat("yyyy/MM/dd").parse(birthDate);
            } catch (ParseException e) {
                System.out.println("birthday format not valid");
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
        return false;
    }


}
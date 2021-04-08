package views;

public class RegisterMenu extends Menu {

    @Override
    public void run() {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        // Todo: check username
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        // Todo: get other properties
    }

    @Override
    public Menu getMenu(int option) {
        return new WelcomeMenu();
    }
}
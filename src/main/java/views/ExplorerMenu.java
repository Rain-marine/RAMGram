package views;

import java.util.Arrays;

public class ExplorerMenu extends Menu {
    public ExplorerMenu() {
        options = Arrays.asList("Search", "Interested tweets, Back");
    }

    @Override
    public void run() {
        System.out.println("**Explorer**");
        boolean isValid;
        String input;

        do {
            for (int i = 1; i < options.size() + 1; i++) {
                System.out.println(i + " : " + options.get(i));
            }
            input = scanner.nextLine();
            isValid = checkValidation(input);
        } while (!isValid);

        int intInput = Integer.parseInt(input);
        if (intInput == 1)
            search();
        else if (intInput == 2) {
            int step = 1;
            while(true) {
                showTopTweets(step);
                step++;
                System.out.println("Load More?(Y/N)");
                input = scanner.nextLine();
                if(input.equals("Y"))
                    continue;
                break;
            }
            getMenu(0).run();
        } else
            getMenu(3).run();
    }

    private void showTopTweets(int step) {

    }

    private void search() {

    }

    @Override
    public Menu getMenu(int option) {
        if(option == 0)
            return new ExplorerMenu();
        return new MainMenu();
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

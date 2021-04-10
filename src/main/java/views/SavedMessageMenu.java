package views;

import java.util.Arrays;

public class SavedMessageMenu extends Menu {

    public SavedMessageMenu() {
        options = Arrays.asList("Back", "New Message");
    }



    @Override
    public Menu getMenu(int option) {
        return null;
    }

    @Override
    public boolean checkValidation(String... input) {
        return false;
    }
}

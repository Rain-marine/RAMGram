package views;

import models.User;

public class PrivatePageMenu extends Menu {
    private User user;

    public PrivatePageMenu(User user) {
        this.user = user;
    }

    @Override
    public void run() {

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

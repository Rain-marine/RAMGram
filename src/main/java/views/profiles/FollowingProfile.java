package views.profiles;

import models.User;
import views.ExplorerMenu;
import views.MainMenu;
import views.Menu;
import views.TimelineMenu;

public class FollowingProfile extends Menu {
    private final User user;
    private final Menu previousMenu;

    public FollowingProfile(User user, Menu previousMenu) {
        this.user = user;
        this.previousMenu = previousMenu;
    }

    @Override
    public void run() {

    }

    @Override
    public Menu getMenu(int option) {
        if (option == 0)
            return new TimelineMenu();
        if (option == 1)
            return new ExplorerMenu();
        return new MainMenu();
    }
}

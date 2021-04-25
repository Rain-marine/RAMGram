package views.profiles;

import models.User;
import views.Menu;

public class PrivateProfile extends Menu {
    //back to search/explorer
    private final User user;

    public PrivateProfile(User user) {
        this.user = user;
    }

    @Override
    public FollowingProfile run() {

        return null;
    }

    @Override
    public Menu getMenu(int option) {
        return null;
    }
}

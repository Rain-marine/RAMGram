package views.profiles;

import models.User;
import views.Menu;

public class ProfileNotVisible extends Menu {

    //back: search/explorer

    public ProfileNotVisible() {

    }

    @Override
    public FollowingProfile run() {

        return null;
    }

    @Override
    public Menu getMenu(int option) {
        return null;
    }
    //username either does not exist or the account is deActive
}

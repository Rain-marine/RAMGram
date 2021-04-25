package controllers;

import models.LoggedUser;
import models.User;
import views.Menu;

public class ProfileAccessController {

    private final long loggedUserId;
    private final long otherUserId;
    private final Menu previousMenu;

    public ProfileAccessController(Menu previousMenu, long otherUserId ) {
        this.loggedUserId = LoggedUser.getLoggedUser().getId();
        this.otherUserId = otherUserId;
        this.previousMenu = previousMenu;
    }

    public Menu checkAccessibility() {


        return null;
    }
}

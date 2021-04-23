package controllers;

import models.LoggedUser;
import models.User;

public class UserController {

    public void blockUser(User userToBlock) {
        //ToDo
        LoggedUser.getLoggedUser().getBlackList().add(userToBlock);
    }

    public void muteUser(User user) {
    }
}

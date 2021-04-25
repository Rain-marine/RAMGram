package controllers;

import models.LoggedUser;
import models.User;
import views.Menu;
import views.profiles.FollowingProfile;
import views.profiles.PrivateProfile;
import views.profiles.ProfileNotVisible;
import views.profiles.PublicProfile;

import java.util.List;

public class ProfileAccessController {

    private final User loggedUser;
    private final long loggedUserId;
    private final User otherUser;
    private final Menu previousMenu;
    private final long otherUserId;

    public ProfileAccessController(Menu previousMenu, User otherUser ) {
        //previous menu may only be explorer
        this.loggedUser = LoggedUser.getLoggedUser();
        this.otherUser = otherUser;
        this.previousMenu = previousMenu;
        this.loggedUserId = LoggedUser.getLoggedUser().getId();
        this.otherUserId = otherUser.getId();
    }

    public Menu checkAccessibility() {
        //is Active>
        if (!otherUser.isActive()){
            return new ProfileNotVisible();
        }
        else {
            //am I following them?
            List<User> loggedUserFollowing = loggedUser.getFollowings();
            for (User user : loggedUserFollowing) {
                if (user.getId() == otherUserId) {
                    return new FollowingProfile(otherUser, previousMenu);
                }
            }
            //am I blocked?
            List<User> blackList =otherUser.getBlackList();
            for (User user : blackList) {
                if (user.getId() == loggedUserId){
                    return new ProfileNotVisible();
                }
            }
            //is their account private?
            if (otherUser.isPublic()){
                return new PublicProfile(otherUser,previousMenu);
            }
            return new PrivateProfile(otherUser);


        }
    }

    public Menu checkFollowing() {
        //Active, public, not block,
        //am I following them?
        List<User> loggedUserFollowing = loggedUser.getFollowings();
        for (User user : loggedUserFollowing) {
            if (user.getId() == otherUserId) {
                return new FollowingProfile(otherUser, previousMenu);
            }
        }
        return new PublicProfile(otherUser,previousMenu);
    }
}

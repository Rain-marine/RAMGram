package views;

import models.User;
import views.profiles.FollowingProfile;

public class Factions  extends Menu{
    //back to personal page menu
    @Override
    public void run() {
        //ToDo
        //show factions list , new faction
        //type faction name to access it
            //show users in that faction
            //commands: delete faction, delete person from it, add person to it, type username to see their profile

    }

    public void editFaction(String factionName){

    }

    public void seeUserProfile (User userToSee){
        new FollowingProfile(userToSee,new Factions()).run();
    }

    @Override
    public Menu getMenu(int option) {
        return null;
    }
}

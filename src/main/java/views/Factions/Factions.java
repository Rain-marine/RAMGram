package views.Factions;

import controllers.FactionsController;
import models.Group;
import models.User;
import views.Menu;
import views.profiles.FollowingProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Factions extends Menu {
    private final FactionsController factionsController;

    public Factions() {
        factionsController = new FactionsController();
    }

    //back to personal page menu
    @Override
    public void run() {
        List<Group> groups = factionsController.getFactions();
        HashMap<String, Group> groupNameToGroup = extractGroupName(groups);
        showFactions(groups);
        while (true) {
            System.out.println("enter faction name! or \"new faction\"! or \"*back\" for back to previous menu!");
            String input = scanner.nextLine();
            if (input.equals("*back"))
                return;
            if (input.equals("new faction")) {
                createNewFaction(groupNameToGroup);
                continue;
            }
        }
        //ToDo
        //show factions list , new faction
        //type faction name to access it
        //show users in that faction
        //commands: delete faction, delete person from it, add person to it, type username to see their profile

    }

    private void createNewFaction(HashMap<String, Group> groupNameToGroup) {
        String name ;
        while (true) {
            System.out.println("enter fraction name!");
            name = scanner.nextLine();
            if (groupNameToGroup.containsKey(name)) {
                System.out.println("you have a group with this name! enter another name");
                continue;
            }
            if (name.equals("*back"))
                return;
            if (name.equals("")) {
                System.out.println("name is invalid");
                continue;
            }
            break;
        }
        List<String> users = new ArrayList<>();
        while (true) {
            System.out.println("enter username to add. just enter to finish");
            String username = scanner.nextLine();
            if (username.equals("*back"))
                return;
            if (username.equals("")) {
                if (users.size() == 0) {
                    System.out.println("group has no member. add at least 1 user!");
                    continue;
                }
                factionsController.insertNewFaction(name, users);
                System.out.println("group created!. you ger back to previous menu!");
                return;
            }
            if (factionsController.canAddToGroup(username)) {
                users.add(username);
                System.out.println("user added!");
                continue;
            }
            System.out.println("can not add this user to group!. you have to follow the user!");
        }
    }

    private HashMap<String, Group> extractGroupName(List<Group> groups) {
        HashMap<String, Group> groupNameToGroup = new HashMap<>();
        for (Group group : groups) {
            groupNameToGroup.put(group.getName(), group);
        }
        groupNameToGroup.put("followers", null);
        groupNameToGroup.put("followings", null);
        return groupNameToGroup;
    }

    private void showFactions(List<Group> groups) {
        System.out.println(1 + " : followers\n2 : followings");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println(i + 3 + " : " + groups.get(i).getName());
        }
    }

    public void editFaction(String factionName) {

    }

    public void seeUserProfile(User userToSee) {
        new FollowingProfile(userToSee, new Factions()).run();
    }

    @Override
    public Menu getMenu(int option) {
        return null;
    }
}
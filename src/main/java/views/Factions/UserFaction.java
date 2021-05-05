package views.Factions;

import controllers.FactionsController;
import controllers.UserController;
import models.Group;
import models.User;
import views.Menu;
import views.profiles.FollowingProfile;

import java.util.HashMap;
import java.util.List;

public class UserFaction extends Menu {
    private final Group group;
    private final FactionsController factionsController;
    private final UserController userController;

    public UserFaction(Group group) {
        this.group = group;
        factionsController = new FactionsController();
        userController = new UserController();
    }

    @Override
    public void run() {
        showOption();
        System.out.println("$" + group.getName() + "$");
        while (true){
            showMembers(factionsController.getGroupMembers(group));
            System.out.println("Enter your request!");
            String input = scanner.nextLine();
            if(input.equals("*back"))
                return;
            if (input.equals("delete")){
                deleteFaction();
                return;
            }
            if (input.startsWith("delete user : ")) {
                deleteUser(input.substring(15));
            }else if (input.startsWith("add user : ")){
                addUser(input.substring(12));
            } else {
                List<User> members = factionsController.getGroupMembers(group);
                HashMap<String, User> usernameToUser = extractUserNameToUser(members);
                if (!usernameToUser.containsKey(input)) {
                    System.out.println("invalid request or username!");
                }else {
                    new FollowingProfile(usernameToUser.get(input), new UserFaction(group));
                }
            }
        }
    }

    private void showMembers(List<User> members) {
        if(members.size() == 0){
            System.out.println("Faction is empty!");
            return;
        }
        for (User member : members) {
            System.out.println(member);
        }
    }

    private void addUser(String username) {
        if (!factionsController.canAddToGroup(username)) {
            System.out.println("Cannot add " + username + " to the group!. You must follow The user");
            return;
        }
        factionsController.addUserToFaction(group, username);
        System.out.println(username + " added to the group!");
    }

    private void deleteUser(String username) {
        List<User> members = factionsController.getGroupMembers(group);
        HashMap<String, User> usernameToUser = extractUserNameToUser(members);
        if (!usernameToUser.containsKey(username)){
            System.out.println("there is no " + username + " in the group!");
            return;
        }
        factionsController.deleteUserFromFaction(group, usernameToUser.get(username));
        System.out.println(username + " deleted from the group!");
    }

    private void deleteFaction() {
        factionsController.deleteFaction(group);
        System.out.println("Faction deleted!");
    }

    private HashMap<String, User> extractUserNameToUser(List<User> members) {
        HashMap<String, User> usernameToUser = new HashMap<>();
        members.forEach(user -> usernameToUser.put(user.getUsername(), user));
        return usernameToUser;
    }

    private void showOption() {
        System.out.println("delete -> delete the faction\n" +
                "delete user : \"username\" -> delete \"username\" from faction\n" +
                "add user : \"username\" -> add \"username\" to faction\n" +
                "\"username\" -> see \"username\" profile\n" +
                "*back -> go to previous menu");
    }

    @Override
    public Menu getMenu(int option) {
        return null;
    }
}
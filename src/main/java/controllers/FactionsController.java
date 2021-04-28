package controllers;

import models.Group;
import models.LoggedUser;
import models.User;
import repository.FactionRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class FactionsController {
    private final UserRepository userRepository;
    private final FactionRepository factionRepository;

    public FactionsController() {
        userRepository = new UserRepository();
        factionRepository = new FactionRepository();
    }

    public void insertNewFaction(String name, List<String> users) {
        User loggedUser = userRepository.getById(LoggedUser.getLoggedUser().getId());
        Group newGroup = new Group(name, loggedUser);

        List<User> members = new ArrayList<>();
        for (String user : users) {
            members.add(userRepository.getByUsername(user));
        }
        newGroup.setMembers(members);
        factionRepository.insert(newGroup);
    }

    public boolean canAddToGroup(String username) {
        User loggedUser = userRepository.getById(LoggedUser.getLoggedUser().getId());
        List<User> followings = loggedUser.getFollowings();
        for (User user : followings) {
            if(username.equals(user.getUsername())){
                if(user.isActive())
                    return true;
            }
        }
        return false;
    }

    public List<Group> getFactions() {
        User loggedUser = userRepository.getById(LoggedUser.getLoggedUser().getId());
        return loggedUser.getGroups();
    }
}

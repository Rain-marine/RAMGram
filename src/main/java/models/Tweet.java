package models;

import java.util.List;

public class Tweet {
    private int id;
    private User user;
    private List<User> usersWhoLiked;
    private List<Tweet> comments;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsersWhoLiked() {
        return usersWhoLiked;
    }

    public void setUsersWhoLiked(List<User> usersWhoLiked) {
        this.usersWhoLiked = usersWhoLiked;
    }

    public List<Tweet> getComments() {
        return comments;
    }

    public void setComments(List<Tweet> comments) {
        this.comments = comments;
    }
}

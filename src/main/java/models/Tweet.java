package models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class Tweet {
    private long id;
    private User user;
    private List<User> usersWhoLiked;
    private List<Tweet> comments;
    private LocalDateTime tweetDateTime;
    private String text;

    public Tweet(User user, String text) {
        this.user = user;
        this.tweetDateTime = LocalDateTime.now();
        this.text = text;
        this.id = System.currentTimeMillis();

    }


    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getTweetDateTime() {
        return tweetDateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

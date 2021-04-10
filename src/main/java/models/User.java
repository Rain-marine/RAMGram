package models;

import java.util.Date;
import java.util.List;

public class User {
    private String username;
    private long id;
    private String name;
    private String email;
    private String password;
    private Date birthday;
    private String phoneNumber;
    private String bio;
    private List<User> followers;
    private List<User> followings;
    private List<User> blackList;
    private List<Tweet> tweets;
    private boolean isPublic;
    private Date lastSeen;
    private List<Tweet> favoriteTweets;
    private List<Message> favoriteMessages;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowings() {
        return followings;
    }

    public List<User> getBlackList() {
        return blackList;
    }

//
//    public void ReTweet (Tweet tweetToBeCopied){
//        Tweet tweetCopy = new Tweet(tweetToBeCopied.getUser() , tweetToBeCopied.getText());
//        this.tweets.add(tweetCopy);
//    }
}

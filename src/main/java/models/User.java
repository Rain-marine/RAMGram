package models;

import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private long userId;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String bio;
    private String lastSeenStatus; // "nobody" , "everyone", "following"
    private Date lastSeen;
    private Date birthday;
    private boolean isPublic;
    private boolean isActive;
    private boolean isBirthDayVisible;
    private boolean isEmailVisible;
    private boolean isPhoneNumberVisible;
    private List<User> followers;
    private List<User> followings;
    private List<User> blackList;
    private List<User> mutedUsers;
    private List<Group> groups;
    private List<Tweet> tweets;
    private List<Tweet> favoriteTweets;
    private List<Message> favoriteMessages;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public void setFollowings(List<User> followings) {
        this.followings = followings;
    }

    public void setBlackList(List<User> blackList) {
        this.blackList = blackList;
    }

    public List<User> getMutedUsers() {
        return mutedUsers;
    }

    public void setMutedUsers(List<User> mutedUsers) {
        this.mutedUsers = mutedUsers;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public boolean isBirthDayVisible() {
        return isBirthDayVisible;
    }

    public void setBirthDayVisible(boolean birthDayVisible) {
        isBirthDayVisible = birthDayVisible;
    }

    public boolean isEmailVisible() {
        return isEmailVisible;
    }

    public void setEmailVisible(boolean emailVisible) {
        isEmailVisible = emailVisible;
    }

    public boolean isPhoneNumberVisible() {
        return isPhoneNumberVisible;
    }

    public void setPhoneNumberVisible(boolean phoneNumberVisible) {
        isPhoneNumberVisible = phoneNumberVisible;
    }

    public List<Tweet> getFavoriteTweets() {
        return favoriteTweets;
    }

    public void setFavoriteTweets(List<Tweet> favoriteTweets) {
        this.favoriteTweets = favoriteTweets;
    }

    public List<Message> getFavoriteMessages() {
        return favoriteMessages;
    }

    public void setFavoriteMessages(List<Message> favoriteMessages) {
        this.favoriteMessages = favoriteMessages;
    }

    public String getLastSeenStatus() {
        return lastSeenStatus;
    }

    public void setLastSeenStatus(String lastSeenStatus) {
        this.lastSeenStatus = lastSeenStatus;
    }

    public long getUserId() {
        return userId;
    }
}

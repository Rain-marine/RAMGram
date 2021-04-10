package models;

import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String bio;
    private Date birthday;
    private Date lastSeen;
    private boolean isPublic;
    private boolean isActive;
    private List<User> followers;
    private List<User> followings;
    private List<User> blackList;
    private List<User> mutedUsers;
    private List<Tweet> tweets;

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
}

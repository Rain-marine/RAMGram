package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    private long userId;

    @Id
    @Column(name = "user_id", unique = true)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "bio")
    private String bio;

    @Column(name = "last_seen_status")
    private String lastSeenStatus; // "nobody" , "everyone", "following"

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_seen")
    private Date lastSeen;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_birthday_visible")
    private boolean isBirthDayVisible;

    @Column(name = "is_email_visible")
    private boolean isEmailVisible;

    @Column(name = "is_phone_number_visible")
    private boolean isPhoneNumberVisible;

    public User(String username, String fullName, String email, String password) {
        this.id = System.currentTimeMillis();
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.isActive = true;
    }

    public User(String username, String fullName, String email, String password, String phoneNumber, String bio, Date birthday) {
        this(username, fullName, email, password);
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.birthday = birthday;
    }

    @ManyToMany
    @JoinTable(
            name = "follower_following",
            joinColumns = @JoinColumn(name = "following_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private List<User> followers;


    @ManyToMany
    @JoinTable(
            name = "follower_following",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> followings;


    @ManyToMany
    @JoinTable(
            name = "user_black_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blocked_id"))
    private List<User> blackList;

//    @ManyToMany
//    @JoinTable(
//            name = "user_black_list",
//            joinColumns = @JoinColumn(name = "blocked_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> blockedByList;

    @ManyToMany
    @JoinTable(
            name = "user_muted",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "muted_id"))
    private List<User> mutedUsers;

//    @ManyToMany
//    @JoinTable(
//            name = "user_muted",
//            joinColumns = @JoinColumn(name = "muted_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> mutedByUsers;

    @ManyToMany
    @JoinTable(
            name = "user_following_request",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> followingRequest;

    @ManyToMany
    @JoinTable(
            name = "user_following_request",
            joinColumns = @JoinColumn(name = "following_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private List<User> followerRequest;

    @OneToMany(mappedBy = "owner")
    private List<Group> groups;

    @OneToMany(mappedBy = "user")
    private List<Tweet> tweets;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_tweets",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    private List<Tweet> favoriteTweets;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_messages",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    private List<Message> favoriteMessages;

    public User() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
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
}

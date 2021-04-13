package models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_tweets",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersWhoLiked;

    @OneToMany(mappedBy = "mainTweet")
    private List<Tweet> comments;

    @ManyToOne
    @JoinColumn(name = "main_tweet_id")
    private Tweet mainTweet;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date tweetDateTime;

    @Column(name = "text")
    private String text;

    public Tweet() {
    }

    public Tweet(User user, String text) {
        this.user = user;
        this.tweetDateTime = new Date();
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

package models;

import javax.persistence.*;
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

    @ManyToMany(cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_liked",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersWhoLiked;

    @OneToMany(mappedBy = "parentTweet", cascade = CascadeType.ALL)
    private List<Tweet> comments;

    @ManyToOne
    @JoinColumn(name = "main_tweet_id")
    private Tweet parentTweet;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date tweetDateTime;

    @Column(name = "text")
    private String text;

    @Column(name = "report_counter",nullable = false)
    private int reportCounter;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_retweet_tweets",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersRetweeted;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_reported_tweets",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersWhoReported;

    public Tweet() {
    }

    public Tweet(User user, String text) {
        this.user = user;
        this.tweetDateTime = new Date();
        this.text = text;
    }




    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getTweetDateTime() {
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

    public void setReportCounter(int reportCounter) {
        this.reportCounter = reportCounter;
    }

    public int getReportCounter() {
        return reportCounter;
    }

    public int getRetweetCounter() {
        return usersRetweeted.size();
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


    public Tweet getParentTweet() {
        return parentTweet;
    }

    public void setParentTweet(Tweet parentTweet) {
        this.parentTweet = parentTweet;
    }

    public List<User> getUsersWhoReported() {
        return usersWhoReported;
    }

    public void setUsersWhoReported(List<User> usersWhoReported) {
        this.usersWhoReported = usersWhoReported;
    }

    public void setTweetDateTime(Date tweetDateTime) {
        this.tweetDateTime = tweetDateTime;
    }

    public List<User> getUsersRetweeted() {
        return usersRetweeted;
    }

    public void setUsersRetweeted(List<User> usersRetweeted) {
        this.usersRetweeted = usersRetweeted;
    }
}
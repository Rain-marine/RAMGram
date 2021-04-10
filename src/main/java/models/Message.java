package models;

import java.util.Date;

public class Message {
    private long id;
    private String text;
    private Date date;
    private User sender;
    private User receiver;

    public Message(String text, User sender, User receiver) {
        this.date = new Date();
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setId(int id) {
        this.id = id;
    }
}

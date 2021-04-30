package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", unique = true)
    private long id;

    @ManyToMany
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    @Column(name = "has_seen")
    private boolean hasSeen;

    @Column(name = "unseen_count")
    private int unseenCount;

    public Chat() {
    }

    public Chat(List<User> users){
        this.users = users;
    };

    public long getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isHasSeen() {
        return hasSeen;
    }

    public void setHasSeen(boolean hasSeen) {
        this.hasSeen = hasSeen;
    }

    public int getUnseenCount() {
        return unseenCount;
    }

    public void setUnseenCount(int unseenCount) {
        this.unseenCount = unseenCount;
    }
}

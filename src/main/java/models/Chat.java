package models;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", unique = true)
    private long id;


    @OneToMany(mappedBy = "chat",cascade = CascadeType.ALL)
    private List<UserChat> userChats;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.PERSIST)
    private List<Message> messages;

    public Chat() {
    }

    public Chat(List<User> users){
        userChats = users.stream().map(it -> new UserChat(it,this)).collect(Collectors.toList());
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

    public List<UserChat> getUserChats() {
        return userChats;
    }

    public void setUserChats(List<UserChat> userChats) {
        this.userChats = userChats;
    }
}

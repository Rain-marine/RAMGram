package models;

import javax.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @Column(name = "notification_id", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType type;

    public Notification() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

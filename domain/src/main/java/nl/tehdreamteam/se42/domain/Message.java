package nl.tehdreamteam.se42.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A Message is a piece of text send by a User to a Conversation.
 *
 * @author Lesley
 * @author Oscar de Leeuw
 */
@Entity
@Table(name = "message")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Message<T> {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    private User sender;
    private LocalDateTime time;

    protected Message() {

    }

    /**
     * Creates a new Message.
     *
     * @param sender  The sender of the Message.
     */
    public Message(User sender) {
        this.sender = sender;
        time = LocalDateTime.now();
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public abstract T getContent();
}

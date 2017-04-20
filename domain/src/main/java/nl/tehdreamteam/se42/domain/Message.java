package nl.tehdreamteam.se42.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * A Message is a piece of text send by a User to a Conversation.
 *
 * @author Lesley
 * @author Oscar de Leeuw
 */
@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @OneToOne(cascade = CascadeType.MERGE)
    private User sender;
    private LocalDateTime time;

    protected Message() {

    }

    /**
     * Creates a new Message.
     *
     * @param content The content of the Message.
     * @param sender  The sender of the Message.
     */
    public Message(String content, User sender) {
        this.content = content;
        this.sender = sender;
        time = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}

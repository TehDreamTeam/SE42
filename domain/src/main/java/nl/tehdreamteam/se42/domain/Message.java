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
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private User sender;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime time;

    private Message() {

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

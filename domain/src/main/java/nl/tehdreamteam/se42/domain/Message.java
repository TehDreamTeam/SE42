package nl.tehdreamteam.se42.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private LocalDateTime time; //TODO Add time to the message.

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
}

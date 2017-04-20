package nl.tehdreamteam.se42.domain;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Oscar de Leeuw
 */
@Entity
public class Conversation {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany(mappedBy = "conversations")
    private List<User> participants;
    @ElementCollection
    @CollectionTable(name = "messages")
    @Column(name = "message_id")
    private List<Message> messages;

    /**
     * Creates a new Conversation.
     */
    public Conversation() {
        this(new LinkedList<>(), new LinkedList<>());
    }

    /**
     * Creates a new Conversation.
     *
     * @param participants The participants of this conversation.
     */
    public Conversation(List<User> participants) {
        this(participants, new LinkedList<>());
    }

    private Conversation(List<User> participants, List<Message> messages) {
        this.participants = participants;
        this.messages = messages;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Adds an User to this Conversation.
     *
     * @param user The User to add to this Conversation.
     */
    public void addUser(User user) {
        participants.add(user);
        user.addConversation(this);
    }

    /**
     * Removes an User from this Conversation.
     *
     * @param user The User to remove from this Conversation.
     */
    public void removeUser(User user) {
        participants.remove(user);
        user.removeConversation(this);
    }

    /**
     * Adds a Message to the Conversation.
     *
     * @param message The Message to add to this Conversation.
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Removes a Message from this Conversation.
     *
     * @param message The Message to remove from this Conversation.
     */
    public void removeMessage(Message message) {
        messages.remove(message);
    }
}
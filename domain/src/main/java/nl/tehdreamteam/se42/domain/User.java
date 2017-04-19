package nl.tehdreamteam.se42.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * An User is a user of the application. An User can send messages in a Conversation.
 *
 * @author Oscar de Leeuw
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private LoginCredentials loginCredentials;
    private List<Conversation> conversations;

    private User() {

    }

    /**
     * Creates a new User.
     *
     * @param loginCredentials The LoginCredentials of the User.
     */
    public User(LoginCredentials loginCredentials) {
        this.loginCredentials = loginCredentials;
    }

    public LoginCredentials getLoginCredentials() {
        return loginCredentials;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    /**
     * Adds a new Conversation to this user.
     *
     * @param conversation The conversation to add to the user.
     */
    public void addConversation(Conversation conversation) {
        conversations.add(conversation);
    }

    /**
     * Removes a Conversation from this user.
     *
     * @param conversation The conversation to remove from this user.
     */
    public void removeConversation(Conversation conversation) {
        conversations.remove(conversation);
    }
}

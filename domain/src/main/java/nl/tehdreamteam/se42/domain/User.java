package nl.tehdreamteam.se42.domain;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * An User is a user of the application. An User can send messages in a Conversation.
 *
 * @author Oscar de Leeuw
 */
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "User.findByUsername",
                query = "SELECT a FROM User AS a WHERE a.loginCredentials.username = :username")
})
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private LoginCredentials loginCredentials;
    @ManyToMany(mappedBy = "participants")
    private List<Conversation> conversations;

    protected User() {

    }

    /**
     * Creates a new User.
     *
     * @param loginCredentials The LoginCredentials of the User.
     */
    public User(LoginCredentials loginCredentials) {
        this.loginCredentials = loginCredentials;
        this.conversations = new LinkedList<>();
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

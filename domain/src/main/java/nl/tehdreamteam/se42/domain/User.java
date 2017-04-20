package nl.tehdreamteam.se42.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

/**
 * An User is a user of the application. An User can send messages in a Conversation.
 *
 * @author Oscar de Leeuw
 */
@Entity
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
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "jnd_usr_conv", joinColumns = @JoinColumn(name = "user_fk"), inverseJoinColumns = @JoinColumn(name = "conv_fk"))
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

package nl.tehdreamteam.se42.data.conversation;

import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;

import java.util.List;

/**
 * A {@code ConversationRepository} defines methods that can be used to get or update {@link Conversation conversations}.
 *
 * @author Lesley
 * @author Oscar de Leeuw
 */
public interface ConversationRepository {

    /**
     * Removes a given Conversation from the database.
     *
     * @param conversation The Conversation to delete from the database.
     */
    void drop(Conversation conversation);

    /**
     * Saves a given Conversation from the database.
     *
     * @param conversation The Conversation to delete from the database.
     */
    void save(Conversation conversation);

    /**
     * Gets all the Conversations for a given User.
     * @param user The User for which to get all Conversations.
     * @return A List of Conversations of which the User is a participant.
     */
    List<Conversation> getConversationsForUser(User user);

    /**
     * Gets all the Messages of a Conversation with the given id.
     * @param id The id of the Conversation.
     * @return A List of Messages.
     */
    List<Message> getMessagesForConversation(long id);
}

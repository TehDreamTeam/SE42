package nl.tehdreamteam.se42.data.conversation;

import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.Message;

/**
 * A {@code ConversationRepository} defines methods that can be used to get or update {@link Conversation conversations}.
 *
 * @author Lesley
 * @author Oscar de Leeuw
 */
public interface ConversationRepository {

    /**
     * Persists a given {@code Conversation} to the database.
     *
     * @param conversation The {@code Conversation} to persist.
     */
    void save(Conversation conversation);

    /**
     * Finds a {@code Conversation} with the given id.
     *
     * @param id The id of the {@code Conversation}.
     * @return A {@code Conversation}.
     */
    Conversation get(long id);

    /**
     * Removes a {@code Conversation} from the database with the given id.
     * Finds the {@code Conversation} object under water.
     *
     * @param id The id of the {@code Conversation} that should be deleted.
     */
    void remove(long id);

    /**
     * Removes a given {@code Conversation} from the database.
     *
     * @param conversation The {@code Conversation} that should be deleted.
     */
    void remove(Conversation conversation);

    /**
     * Adds a {@code Message} to a {@code Conversation} with the given id.
     * Find the {@code Conversation} object with the given id under water.
     *
     * @param conversationId The id of the {@code Conversation}.
     * @param message        The {@code Message} that should be added to the {@code Conversation}.
     */
    void addMessage(long conversationId, Message message);

    /**
     * Adds a {@code Message} to a given {@code Conversation}.
     *
     * @param conversation The {@code Conversation} to which the {@code Message} should be added.
     * @param message      The {@code Message} that should be added to the {@code Conversation}.
     */
    void addMessage(Conversation conversation, Message message);
}

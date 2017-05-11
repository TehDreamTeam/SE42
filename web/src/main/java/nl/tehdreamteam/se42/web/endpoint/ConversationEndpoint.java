package nl.tehdreamteam.se42.web.endpoint;

import nl.tehdreamteam.se42.domain.Message;

import java.util.List;

/**
 * Web endpoint for controlling {@code Conversations}.
 *
 * @author Oscar de Leeuw
 */
public interface ConversationEndpoint {

    /**
     * Creates a {@link Message} and adds it to the database.
     *
     * @param token          A valid token that is needed to verify a user.
     * @param content        The content of the {@code Message}.
     * @param conversationId The id of the {@link nl.tehdreamteam.se42.domain.Conversation} to which the {@code Message} should be added.
     */
    void addMessage(String token, String content, long conversationId);

    /**
     * Adds a {@link nl.tehdreamteam.se42.domain.User} to a {@link nl.tehdreamteam.se42.domain.Conversation}.
     *
     * @param token          A valid token that is needed to verify a user.
     * @param userId         The id of the {@code User} that should be added to the {@code Conversation}.
     * @param conversationId The id of the {@code Conversation} to which the {@code User} should be added.
     */
    void addUserToConversation(String token, long userId, long conversationId);

    /**
     * Gets all the {@code Messages} of a {@link nl.tehdreamteam.se42.domain.Conversation}.
     *
     * @param token          A valid token that is needed to verify a user.
     * @param conversationId The id of the {@code Conversation} for which to fetch all the {@code Messages}.
     * @return A list of all the {@code Messages}.
     */
    List<Message> getMessages(String token, long conversationId);
}

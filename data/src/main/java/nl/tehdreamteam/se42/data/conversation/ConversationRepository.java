package nl.tehdreamteam.se42.data.conversation;

import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;

import java.util.List;

/**
 * A {@code ConversationRepository} defines methods that can be used to get or update {@link Conversation conversations}.
 *
 * @author Lesley
 */
public interface ConversationRepository {

    void save(Conversation conversation);

    List<Conversation> getConversationsForUser(User user);

    List<Message> getMessagesForConversation(long id);
}

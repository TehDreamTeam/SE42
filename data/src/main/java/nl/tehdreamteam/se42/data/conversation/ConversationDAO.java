package nl.tehdreamteam.se42.data.conversation;

import nl.tehdreamteam.se42.data.dao.DAO;
import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;

import java.util.List;

/**
 * @author Oscar de Leeuw
 */
public interface ConversationDAO extends DAO<Long, Conversation> {

    List<Message> findMessagesById(long id);

    List<Conversation> findByUser(User user);
}

package nl.tehdreamteam.se42.data.conversation.hibernate;

import nl.tehdreamteam.se42.data.conversation.ConversationDAO;
import nl.tehdreamteam.se42.data.conversation.ConversationRepository;
import nl.tehdreamteam.se42.data.repository.HibernateRepository;
import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;

/**
 * A {@code ConversationHibernateRepository} defines the communication between the database and a {@code ConversationDao}.
 *
 * @author Oscar de Leeuw
 */
public class ConversationHibernateRepository extends HibernateRepository<Long, Conversation, ConversationDAO> implements ConversationRepository {

    @Override
    protected ConversationDAO createDao(EntityManager manager) {
        return new ConversationHibernateDAO(manager, Conversation.class);
    }

    @Override
    public void drop(Conversation conversation) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void save(Conversation conversation) {
        Function<ConversationDAO, Void> function = dao -> {
            dao.create(conversation);
            return null;
        };

        super.performTransaction(function);
    }

    @Override
    public List<Conversation> getConversationsForUser(User user) {
        Function<ConversationDAO, List<Conversation>> function = dao -> dao.findByUser(user);

        return super.performTransaction(function);
    }

    @Override
    public List<Message> getMessagesForConversation(long id) {
        Function<ConversationDAO, List<Message>> function = dao -> dao.findMessagesById(id);

        return super.performTransaction(function);
    }
}

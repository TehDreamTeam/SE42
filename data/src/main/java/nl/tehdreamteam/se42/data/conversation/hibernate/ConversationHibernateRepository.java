package nl.tehdreamteam.se42.data.conversation.hibernate;

import nl.tehdreamteam.se42.data.conversation.ConversationDAO;
import nl.tehdreamteam.se42.data.conversation.ConversationRepository;
import nl.tehdreamteam.se42.data.repository.HibernateRepository;
import nl.tehdreamteam.se42.domain.Conversation;

import javax.persistence.EntityManager;
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
    public Conversation find(long id) {
        Function<ConversationDAO, Conversation> function = dao -> dao.find(id);

        return super.performTransaction(function);
    }

    @Override
    public void remove(long id) {
        Conversation conversation = find(id);
        remove(conversation);
    }

    @Override
    public void remove(Conversation conversation) {
        Function<ConversationDAO, Void> function = dao -> {
            dao.remove(conversation);
            return null;
        };

        super.performTransaction(function);
    }
}

package nl.tehdreamteam.se42.data.conversation.hibernate;

import nl.tehdreamteam.se42.data.conversation.ConversationDAO;
import nl.tehdreamteam.se42.data.conversation.ConversationRepository;
import nl.tehdreamteam.se42.data.repository.HibernateRepository;
import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.Message;

import javax.persistence.EntityManager;

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
    public void save(Conversation conversation) {
        super.create(conversation);
    }

    @Override
    public Conversation get(long id) {
        return super.find(id);
    }

    @Override
    public void remove(long id) {
        Conversation conversation = get(id);
        remove(conversation);
    }

    @Override
    public void remove(Conversation conversation) {
        super.remove(conversation);
    }

    @Override
    public void addMessage(long conversationId, Message message) {
        Conversation conv = get(conversationId);
        addMessage(conv, message);
    }

    @Override
    public void addMessage(Conversation conversation, Message message) {
        conversation.addMessage(message);
        edit(conversation);
    }
}

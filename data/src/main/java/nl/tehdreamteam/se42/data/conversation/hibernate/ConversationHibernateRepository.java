package nl.tehdreamteam.se42.data.conversation.hibernate;

import nl.tehdreamteam.se42.data.conversation.ConversationDAO;
import nl.tehdreamteam.se42.data.conversation.ConversationRepository;
import nl.tehdreamteam.se42.data.repository.HibernateRepository;
import nl.tehdreamteam.se42.domain.Conversation;

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

}

package nl.tehdreamteam.se42.data.context.impl;

import nl.tehdreamteam.se42.data.dao.ConversationDAO;
import nl.tehdreamteam.se42.data.dao.impl.ConversationHibernateDAO;
import nl.tehdreamteam.se42.domain.Conversation;

import javax.persistence.EntityManager;

/**
 * A {@code ConversationHibernateContext} defines the communication between the database and a {@code ConversationDao}.
 *
 * @author Oscar de Leeuw
 */
public class ConversationHibernateContext extends HibernateContext<Long, Conversation, ConversationDAO> {
    @Override
    protected ConversationDAO createDao(EntityManager manager) {
        return new ConversationHibernateDAO(manager, Conversation.class);
    }
}

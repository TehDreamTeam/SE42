package nl.tehdreamteam.se42.data.dao.impl;

import nl.tehdreamteam.se42.data.dao.ConversationDAO;
import nl.tehdreamteam.se42.domain.Conversation;

import javax.persistence.EntityManager;

/**
 * Implementation of the ConversationDAO interface.
 * Extends HibernateDAO for default DAO operations as described in the DAO interface.
 *
 * @author Oscar de Leeuw
 */
public class HibernateConversationDAO extends HibernateDAO<Long, Conversation> implements ConversationDAO {
    /**
     * Creates a new HibernateConversationDAO.
     *
     * @param entityManager The EntityManager for managing the JPA.
     * @param type          The type of the generic T.
     */
    public HibernateConversationDAO(EntityManager entityManager, Class<Conversation> type) {
        super(entityManager, type);
    }
}
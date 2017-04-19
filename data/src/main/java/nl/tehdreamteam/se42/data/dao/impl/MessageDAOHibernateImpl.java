package nl.tehdreamteam.se42.data.dao.impl;

import nl.tehdreamteam.se42.data.dao.MessageDAO;
import nl.tehdreamteam.se42.domain.Message;

import javax.persistence.EntityManager;

/**
 * Implementation of the MessageDAO interface.
 * Extends DefaultDAOHibernateImpl for default DAO operations as described in the DAO interface.
 *
 * @author Oscar de Leeuw
 */
public class MessageDAOHibernateImpl extends DefaultDAOHibernateImpl<Long, Message> implements MessageDAO {

    /**
     * Creates a new MessageDAOHibernateImpl.
     *
     * @param entityManager The EntityManager for managing the JPA.
     */
    public MessageDAOHibernateImpl(EntityManager entityManager) {
        super(entityManager, Message.class);
    }
}

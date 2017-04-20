package nl.tehdreamteam.se42.data.message.hibernate;

import nl.tehdreamteam.se42.data.dao.HibernateDAO;
import nl.tehdreamteam.se42.data.message.MessageDAO;
import nl.tehdreamteam.se42.domain.Message;

import javax.persistence.EntityManager;

/**
 * Implementation of the MessageDAO interface.
 * Extends HibernateDAO for default DAO operations as described in the DAO interface.
 *
 * @author Oscar de Leeuw
 */
public class MessageHibernateDAO extends HibernateDAO<Long, Message> implements MessageDAO {

    /**
     * Creates a new MessageHibernateDAO.
     *
     * @param entityManager The EntityManager for managing the JPA.
     */
    public MessageHibernateDAO(EntityManager entityManager) {
        super(entityManager, Message.class);
    }
}

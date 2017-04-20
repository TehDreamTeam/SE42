package nl.tehdreamteam.se42.data.message.hibernate;

import nl.tehdreamteam.se42.data.message.MessageDAO;
import nl.tehdreamteam.se42.data.message.MessageRepository;
import nl.tehdreamteam.se42.data.repository.HibernateRepository;
import nl.tehdreamteam.se42.domain.Message;

import javax.persistence.EntityManager;

/**
 * A {@code MessageHibernateRepository} defines communication between the database and a {@link MessageDAO}.
 *
 * @author Lesley
 */
public final class MessageHibernateRepository extends HibernateRepository<Long, Message, MessageDAO> implements MessageRepository {

    @Override
    protected MessageDAO createDao(EntityManager manager) {
        return new MessageHibernateDAO(manager);
    }

}

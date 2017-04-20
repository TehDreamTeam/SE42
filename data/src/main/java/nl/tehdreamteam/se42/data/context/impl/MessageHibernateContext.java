package nl.tehdreamteam.se42.data.context.impl;

import nl.tehdreamteam.se42.data.dao.MessageDAO;
import nl.tehdreamteam.se42.data.dao.impl.MessageHibernateDAO;
import nl.tehdreamteam.se42.domain.Message;

import javax.persistence.EntityManager;

/**
 * A {@code MessageHibernateContext} defines communication between the database and a {@link MessageDAO}.
 *
 * @author Lesley
 */
public final class MessageHibernateContext extends HibernateContext<Long, Message, MessageDAO> {

    @Override
    protected MessageDAO createDao(EntityManager manager) {
        return new MessageHibernateDAO(manager);
    }

}

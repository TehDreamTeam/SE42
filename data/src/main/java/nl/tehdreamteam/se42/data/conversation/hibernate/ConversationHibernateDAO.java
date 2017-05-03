package nl.tehdreamteam.se42.data.conversation.hibernate;

import nl.tehdreamteam.se42.data.conversation.ConversationDAO;
import nl.tehdreamteam.se42.data.dao.HibernateDAO;
import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of the ConversationDAO interface.
 * Extends HibernateDAO for default DAO operations as described in the DAO interface.
 *
 * @author Oscar de Leeuw
 */
public class ConversationHibernateDAO extends HibernateDAO<Long, Conversation> implements ConversationDAO {
    /**
     * Creates a new ConversationHibernateDAO.
     *
     * @param entityManager The EntityManager for managing the JPA.
     * @param type          The type of the generic T.
     */
    public ConversationHibernateDAO(EntityManager entityManager, Class<Conversation> type) {
        super(entityManager, type);
    }

    @Override
    public List<Message> findMessagesById(long id) {
        List<Message> l = find(id).getMessages();
        Hibernate.initialize(l);

        return l;
    }

    @Override
    public List<Conversation> findByUser(User user) {
        Query query = super.em.createNamedQuery("Conversation.findByUser");
        query.setParameter("username", user.getLoginCredentials().getUsername());

        List<Conversation> result = (List<Conversation>) query.getResultList();
        result.forEach(c -> Hibernate.initialize(c.getMessages()));
        result.forEach(c -> Hibernate.initialize(c.getParticipants()));

        return result;
    }
}

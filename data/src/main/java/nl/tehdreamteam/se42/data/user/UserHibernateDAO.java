package nl.tehdreamteam.se42.data.user;

import nl.tehdreamteam.se42.data.dao.DAO;
import nl.tehdreamteam.se42.data.dao.HibernateDAO;
import nl.tehdreamteam.se42.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Implementation of the {@link UserDAO} interface.
 * Extends {@link HibernateDAO} for default {@link DAO} operations as described in the {@code DAO} interface.
 *
 * @author Lesley Vente
 */
public final class UserHibernateDAO extends HibernateDAO<Long, User> implements UserDAO {

    /**
     * Initializes this {@code UserHibernateDAO} using the given entity manager.
     *
     * @param entityManager The EntityManager for managing the JPA.
     */
    public UserHibernateDAO(EntityManager entityManager) {
        super(entityManager, User.class);
    }

    @Override
    public User findByUsername(String username) {
        EntityManager manager = super.em;
        TypedQuery<User> query = manager.createNamedQuery("User.findByUsername", User.class);
        query.setParameter("username", username);

        User fetchedUser = query.getSingleResult();
        return fetchedUser;
    }
}

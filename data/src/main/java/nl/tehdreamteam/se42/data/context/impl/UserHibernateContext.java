package nl.tehdreamteam.se42.data.context.impl;

import nl.tehdreamteam.se42.data.dao.UserDAO;
import nl.tehdreamteam.se42.data.dao.impl.HibernateUserDAO;
import nl.tehdreamteam.se42.domain.User;

import javax.persistence.EntityManager;
import java.util.function.Function;

/**
 * A {@code UserHibernateContext} defines communication between the database and a {@link UserDAO}.
 *
 * @author Lesley
 */
public final class UserHibernateContext extends HibernateContext<Long, User, UserDAO> {

    /**
     * Finds a {@link User}, which has the given {@code username}, in the database.
     *
     * @param username The {@code username} of the {@code User} to search the database for.
     * @return The found {@code User}.
     */
    public User findByUsername(String username) {
        Function<UserDAO, User> findAllFunction = dao -> dao.findByUsername(username);

        return super.performTransaction(findAllFunction);
    }

    @Override
    protected UserDAO createDao(EntityManager manager) {
        return new HibernateUserDAO(manager);
    }

}

package nl.tehdreamteam.se42.data.user.hibernate;

import nl.tehdreamteam.se42.data.repository.HibernateRepository;
import nl.tehdreamteam.se42.data.user.UserDAO;
import nl.tehdreamteam.se42.data.user.UserRepository;
import nl.tehdreamteam.se42.domain.User;

import javax.persistence.EntityManager;
import java.util.function.Function;

/**
 * A {@code UserHibernateRepository} defines communication between the database and a {@link UserDAO}.
 *
 * @author Lesley
 */
public final class UserHibernateRepository extends HibernateRepository<Long, User, UserDAO> implements UserRepository {

    @Override
    public User findByUsername(String username) {
        Function<UserDAO, User> findAllFunction = dao -> dao.findByUsername(username);

        return super.performTransaction(findAllFunction);
    }

    @Override
    protected UserDAO createDao(EntityManager manager) {
        return new UserHibernateDAO(manager);
    }

}

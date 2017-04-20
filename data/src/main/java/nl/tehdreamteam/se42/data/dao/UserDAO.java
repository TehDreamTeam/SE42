package nl.tehdreamteam.se42.data.dao;

import nl.tehdreamteam.se42.domain.User;

/**
 * Manages the data for the {@link User} class.
 *
 * @author Lesley
 */
public interface UserDAO extends DAO<Long, User> {

    /**
     * Finds a {@link User}, which has the given {@code username}, in the database.
     *
     * @param username The {@code username} of the {@code User} to search the database for.
     * @return The found {@code User}.
     */
    User findByUsername(String username);

}

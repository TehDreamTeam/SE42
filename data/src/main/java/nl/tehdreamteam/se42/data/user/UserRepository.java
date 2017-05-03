package nl.tehdreamteam.se42.data.user;

import nl.tehdreamteam.se42.domain.User;

/**
 * A {@link UserRepository} defines methods that can be used to get or update {@link User users}.
 *
 * @author Lesley
 */
public interface UserRepository {

    /**
     * Finds a {@link User}, which has the given {@code username}, in the database.
     *
     * @param username The {@code username} of the {@code User} to search the database for.
     * @return The found {@code User}.
     */
    User get(String username);

    /**
     * Saves a {@link User} to the database.
     *
     * @param user The {@code user} to save to the database.
     */
    void save(User user);

    /**
     * Removes a {@link User} from the database.
     *
     * @param user The {@code user} to remove from the database.
     */
    void remove(User user);

    /**
     * Removes a {@link User} from the database by finding the {@code User} by its {@code id}.
     *
     * @param id The {@code id} of the {@code User} to remove from the database.
     */
    void remove(long id);
}

package nl.tehdreamteam.se42.logic.controller;

import com.jnape.palatable.lambda.adt.Either;
import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.logic.exception.ServerError;

import java.util.List;

/**
 * The {@code UserController} interface can be used to access {@link User} data.
 * The controller will execute validation on all the data that goes through it.
 *
 * @author Oscar de Leeuw
 */
public interface UserController {

    /**
     * Checks whether the given credentials belong to an existing {@code User}.
     * If they do then the corresponding {@code User} will be set to the right value.
     * If an error occurred it will be put in the left value.
     *
     * @param username The username of the {@code User}.
     * @param password The password of the {@code User}.
     * @return A {@code Either} with a failure value as the left value and success value as the right value.
     */
    Either<ServerError, User> login(String username, String password);

    /**
     * Registers a new {@code User} with the application.
     * If the {@code User} could be registered the {@code User} object will be set to the right value.
     * If an error occurred it will be put in the left value.
     *
     * @param username The username of the {@code User}.
     * @param password the password of the {@code User}.
     * @return A {@code Either} with a failure value as the left value and success value as the right value.
     */
    Either<ServerError, User> register(String username, String password);

    /**
     * Gets all the {@code Conversations} of a {@code User} with the given id.
     * If the {@code Conversations} could be retrieved they will be put as the right value.
     * If an error occurred it will be put in the left value.
     *
     * @param userId The id of the {@code User}.
     * @return A {@code Either} with a failure value as the left value and success value as the right value.
     */
    Either<ServerError, List<Conversation>> getConversations(long userId);
}

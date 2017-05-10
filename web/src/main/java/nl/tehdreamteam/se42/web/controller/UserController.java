package nl.tehdreamteam.se42.web.controller;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.Service;

import java.util.List;

/**
 * Defines functions that specific {@link Service Services} can use to serve content.
 */
public interface UserController {

    /**
     * Logs a {@link User} in, given that the specified {@code username} and {@code password} are correct, and returns
     * a {@code Token value} that temporarily identifies the logged-in {@code User}.
     *
     * @param username The {@code username} of the {@code User} to login.
     * @param password The {@code password} of the {@code User} to login.
     * @return A {@code Token value} that temporarily identifies the logged-in {@code User}.
     */
    String login(String username, String password);

    /**
     * Gets a List of all the ids of the {@code Conversations} that the user participates in.
     *
     * @param token A valid token that is needed to verify a user.
     * @return A List of all the id's.
     */
    List<Long> getConversations(String token);

}

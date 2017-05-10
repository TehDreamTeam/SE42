package nl.tehdreamteam.se42.web.controller;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.Service;

/**
 * Defines functions that specific {@link Service Services} can use to serve content.
 */
public interface UserController {

    /**
     * Logs a {@link User} in, given that the specified {@code username} and {@code password} are correct, and returns
     * a {@code token} that temporarily identifies the logged-in {@code User}.
     *
     * @param username The {@code username} of the {@code User} to login.
     * @param password The {@code password} of the {@code User} to login.
     * @return A {@code token} that temporarily identifies the logged-in {@code User}.
     */
    String login(String username, String password); // TODO, should not be a String, probably a dedicated Token class

}

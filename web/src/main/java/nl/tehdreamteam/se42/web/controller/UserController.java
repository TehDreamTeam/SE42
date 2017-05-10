package nl.tehdreamteam.se42.web.controller;

import nl.tehdreamteam.se42.domain.User;

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

}

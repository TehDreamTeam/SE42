package nl.tehdreamteam.se42.web.token;

import nl.tehdreamteam.se42.domain.User;

/**
 * Strategy for generating a token.
 *
 * @author Oscar de Leeuw
 */
@FunctionalInterface
public interface TokenGenerationStrategy {

    /**
     * Generates a {@code String} that can be used a token with the given {@code User}.
     *
     * @param user The {@code User} for which to generate a token.
     * @return A {@code String} that represents a token.
     */
    String generateToken(User user);
}

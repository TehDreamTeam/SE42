package nl.tehdreamteam.se42.web.token;

import nl.tehdreamteam.se42.domain.User;

import java.time.LocalDateTime;

/**
 * Factory for creating {@code Tokens}.
 *
 * @author Oscar de Leeuw
 */
public class TokenFactory {

    private TokenGenerationStrategy strategy;

    /**
     * Creates a new {@code TokenFactory}.
     */
    public TokenFactory() {
        this.strategy = new HashTokenStrategy();
    }

    public void setStrategy(TokenGenerationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Creates a {@code Token} based on a {@code User}.
     *
     * @param user The {@code User} for which to generate a {@code Token}.
     * @return A {@code Token} object.
     */
    public Token createToken(User user) {
        return new Token(user, strategy, LocalDateTime.now());
    }
}

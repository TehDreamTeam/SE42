package nl.tehdreamteam.se42.web.token;

import nl.tehdreamteam.se42.domain.User;

import java.time.LocalDateTime;

/**
 * A {@code Token} is an object for keeping track of a logged-in {@code User}.
 *
 * @author Oscar de Leeuw
 */
public class Token {

    private String value;
    private User user;
    private LocalDateTime created;
    private LocalDateTime lastUsed;

    /**
     * Creates a new {@code Token}.
     *
     * @param user       The {@code User} for which to create a {@code Token}.
     * @param strategy   The {@code Strategy} for generating a {@code Token}.
     * @param createTime The time of creation.
     */
    public Token(User user, TokenGenerationStrategy strategy, LocalDateTime createTime) {
        this.user = user;
        this.created = createTime;
        this.value = strategy.generateToken(user);
        this.lastUsed = createTime;
    }

    public String getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastUsed() {
        return lastUsed;
    }
}

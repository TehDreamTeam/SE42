package nl.tehdreamteam.se42.web.token;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.token.generator.TokenGenerator;

import java.util.Optional;

/**
 * A {@code TokenRegistry} collects and manages all {@link Token Tokens}.
 */
public interface TokenRegistry {

    /**
     * Registers a {@link Token} for the given {@link User}. If the {@code user} already has an active {@code token},
     * the currently active {@code token} is invalidated, and a new one will be generated.
     *
     * @param user The {@code user} to register a {@code token} for.
     * @return A generated {@code token} for the given {@code user}.
     */
    Token register(User user);

    /**
     * Deregisters the given {@link Token} for a {@link User}.
     *
     * @param token The {@code token} to deregister.
     */
    void deregister(Token token);

    /**
     * Gets the {@link Token} for the given {@link User}. If the given {@code user} has no {@code token} currently
     * registered, an empty {@link Optional} will be returned.
     *
     * @param user The {@code user} to get a {@code token} for.
     * @return An {@code optional} containing the {@code user}'s {@code token}.
     * @see Optional#empty()
     */
    Optional<Token> get(User user);

    /**
     * Sets the {@link TokenGenerator} for this {@code TokenRegistry}.
     *
     * @param generator The new {@code generator} to use for this {@code TokenRegistry}.
     */
    void setGenerator(TokenGenerator generator);

    /**
     * Gets the {@link TokenGenerator} for this {@code TokenRegistry}.
     *
     * @return The {@code generator} that this {@code TokenRegistry} uses.
     */
    TokenGenerator getGenerator();

    /**
     * Gets the {@link Token} for the given {@link User}. If the given {@code user} has {@code token} currently
     * registered, a new {@code token} will be registered.
     *
     * @param user The {@code user} to get, or registered, a {@code token} for.
     * @return A {@code token} for the given {@code user}.
     */
    default Token getOrRegister(User user) {
        return get(user).orElseGet(() -> register(user));
    }

}

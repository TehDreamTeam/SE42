package nl.tehdreamteam.se42.web.token.impl;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.token.AbstractTokenRegistry;
import nl.tehdreamteam.se42.web.token.Token;
import nl.tehdreamteam.se42.web.token.TokenRegistry;
import nl.tehdreamteam.se42.web.token.generator.TokenGenerator;
import nl.tehdreamteam.se42.web.token.generator.impl.SimpleTokenGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of {@link TokenRegistry} that stores {@link Token Tokens} based on their hash.
 *
 * @see HashMap
 */
public class HashTokenRegistry extends AbstractTokenRegistry {

    private static final Logger logger = LogManager.getLogger(HashTokenRegistry.class);

    private final Map<User, Token> tokens = new HashMap<>();

    /**
     * Initializes this {@code HashTokenRegistry} using a {@link SimpleTokenGenerator}.
     *
     * @see AbstractTokenRegistry#AbstractTokenRegistry()
     */
    public HashTokenRegistry() {
        super();
    }

    /**
     * Initializes this {@code HashTokenRegistry} using the given {@link TokenGenerator}.
     *
     * @param generator The {@code generator} this {@code HashTokenRegistry} uses to generate {@link Token Tokens}.
     * @see AbstractTokenRegistry#AbstractTokenRegistry(TokenGenerator)
     */
    public HashTokenRegistry(TokenGenerator generator) {
        super(generator);
    }

    @Override
    public Token register(User user) {
        Token token = getAndDeregisterOptionally(user);
        tokens.put(user, token);

        return token;
    }

    private Token getAndDeregisterOptionally(User user) {
        Token token = tokens.get(user);
        if (Objects.nonNull(token)) {
            deregister(token);
        }

        token = getGenerator().generate(user);

        logger.debug("Generated new token '{}' for user '{}'.", token.getId(), user);

        return token;
    }

    @Override
    public void deregister(Token token) {
        logger.debug("Invalidating token '{}' for user '{}'.", token.getId(), token.getUser());

        token.invalidate();
        tokens.remove(token.getUser(), token);
    }

    @Override
    public Optional<Token> get(User user) {
        return Optional.ofNullable(tokens.get(user));
    }

}

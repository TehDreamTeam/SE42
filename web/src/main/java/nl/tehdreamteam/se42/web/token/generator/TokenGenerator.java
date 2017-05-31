package nl.tehdreamteam.se42.web.token.generator;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.token.Token;
import nl.tehdreamteam.se42.web.token.generator.id.TokenIdGenerator;

/**
 * A {@code TokenGenerator} provides the strategy to generate {@link Token Tokens}.
 */
public interface TokenGenerator {

    /**
     * Generates a {@link Token} for the given {@link User}.
     *
     * @return A generated {@code Token} for the given {@code user}.
     */
    Token generate(User user);

    /**
     * Sets the {@link TokenIdGenerator}, used to generate {@code id}s for {@link Token Tokens}.
     *
     * @param generator The new {@code generator} this {@code TokenGenerator} should use.
     */
    void setIdGenerator(TokenIdGenerator generator);

    /**
     * Gets the {@link TokenIdGenerator}, used to generate {@code id}s for {@link Token Tokens}.
     *
     * @return The {@code generator} this {@code TokenGenerator} uses.
     */
    TokenIdGenerator getIdGenerator();

}

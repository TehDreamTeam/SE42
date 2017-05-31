package nl.tehdreamteam.se42.web.token.generator;

import nl.tehdreamteam.se42.web.token.generator.id.TokenIdGenerator;
import nl.tehdreamteam.se42.web.token.generator.id.impl.UUIDTokenIdGenerator;

/**
 * Provides a default implementation for {@link TokenGenerator}.
 */
public abstract class AbstractTokenGenerator implements TokenGenerator {

    private TokenIdGenerator generator;

    /**
     * Initializes this {@link TokenGenerator} using a {@link UUIDTokenIdGenerator}.
     *
     * @see #AbstractTokenGenerator(TokenIdGenerator)
     */
    public AbstractTokenGenerator() {
        this(new UUIDTokenIdGenerator());
    }

    /**
     * Initializes this {@link TokenGenerator} using the given {@link TokenIdGenerator}.
     *
     * @param generator The {@code generator} that will be used to generate {@code id}s with.
     */
    public AbstractTokenGenerator(TokenIdGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void setIdGenerator(TokenIdGenerator generator) {
        this.generator = generator;
    }

    @Override
    public TokenIdGenerator getIdGenerator() {
        return generator;
    }

}

package nl.tehdreamteam.se42.web.token;

import nl.tehdreamteam.se42.web.token.generator.TokenGenerator;
import nl.tehdreamteam.se42.web.token.generator.impl.SimpleTokenGenerator;

/**
 * Provides a default implementation for {@link TokenRegistry}.
 */
public abstract class AbstractTokenRegistry implements TokenRegistry {

    private TokenGenerator generator;

    /**
     * Initializes this {@link TokenRegistry} using a {@link SimpleTokenGenerator} to generate {@link Token Tokens}.
     */
    public AbstractTokenRegistry() {
        this(new SimpleTokenGenerator());
    }

    /**
     * Initializes this {@link TokenRegistry} using the given {@link TokenGenerator}.
     *
     * @param generator The {@code generator} that generates {@link Token Tokens}.
     */
    public AbstractTokenRegistry(TokenGenerator generator) {
        this.generator = generator;
    }

    @Override
    public TokenGenerator getGenerator() {
        return generator;
    }

    @Override
    public void setGenerator(TokenGenerator generator) {
        this.generator = generator;
    }

}

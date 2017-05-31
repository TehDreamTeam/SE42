package nl.tehdreamteam.se42.web.token.generator.impl;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.token.Token;
import nl.tehdreamteam.se42.web.token.generator.AbstractTokenGenerator;
import nl.tehdreamteam.se42.web.token.generator.TokenGenerator;
import nl.tehdreamteam.se42.web.token.generator.id.TokenIdGenerator;
import nl.tehdreamteam.se42.web.token.impl.SimpleToken;

/**
 * Implementation of {@link TokenGenerator} that generates {@link SimpleToken SimpleTokens}.
 */
public class SimpleTokenGenerator extends AbstractTokenGenerator {

    /**
     * Initializes this {@code SimpleTokenGenerator} using a predefined {@link TokenIdGenerator}.
     *
     * @see AbstractTokenGenerator#AbstractTokenGenerator()
     */
    public SimpleTokenGenerator() {
        super();
    }

    /**
     * Initializes this {@code SimpleTokenGenerator} using the given {@code generator}.
     *
     * @param generator The {@code generator} used to generate {@code id}s.
     * @see AbstractTokenGenerator#AbstractTokenGenerator(TokenIdGenerator)
     */
    public SimpleTokenGenerator(TokenIdGenerator generator) {
        super(generator);
    }

    @Override
    public Token generate(User user) {
        String id = getIdGenerator().generate();
        return new SimpleToken(id, user);
    }

}

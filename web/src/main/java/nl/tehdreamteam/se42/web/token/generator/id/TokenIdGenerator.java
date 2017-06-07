package nl.tehdreamteam.se42.web.token.generator.id;

import nl.tehdreamteam.se42.web.token.Token;

/**
 * A {@code TokenIdGenerator} provides a way to generate {@code id}s for {@link Token Tokens}.
 */
public interface TokenIdGenerator {

    /**
     * Generates an {@code id} used to identify a {@link Token}.
     *
     * @return A {@link String} containing the {@code id} used to identify a {@code Token}.
     */
    String generate();

}

package nl.tehdreamteam.se42.web.token.generator.id.impl;

import nl.tehdreamteam.se42.web.token.Token;
import nl.tehdreamteam.se42.web.token.generator.id.TokenIdGenerator;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * A {@code UUIDTokenIdGenerator} is an implementation of {@link TokenIdGenerator} that uses Java's {@link UUID} to
 * identify a {@link Token}.
 */
public class UUIDTokenIdGenerator implements TokenIdGenerator {

    private final Random random = new SecureRandom();

    @Override
    public String generate() {
        return new UUID(random.nextLong(), random.nextLong()).toString();
    }

}

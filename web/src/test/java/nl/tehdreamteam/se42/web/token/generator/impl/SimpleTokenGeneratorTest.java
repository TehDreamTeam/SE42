package nl.tehdreamteam.se42.web.token.generator.impl;

import nl.tehdreamteam.se42.web.token.generator.AbstractTokenGeneratorTest;
import nl.tehdreamteam.se42.web.token.generator.TokenGenerator;
import nl.tehdreamteam.se42.web.token.generator.id.TokenIdGenerator;

public class SimpleTokenGeneratorTest extends AbstractTokenGeneratorTest {

    @Override
    protected TokenGenerator constructDefaultTokenGenerator() {
        return new SimpleTokenGenerator();
    }

    @Override
    protected TokenGenerator constructTokenGenerator(TokenIdGenerator idGenerator) {
        return new SimpleTokenGenerator(idGenerator);
    }

}
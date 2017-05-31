package nl.tehdreamteam.se42.web.token.impl;

import nl.tehdreamteam.se42.web.token.AbstractTokenRegistryTest;
import nl.tehdreamteam.se42.web.token.TokenRegistry;
import nl.tehdreamteam.se42.web.token.generator.TokenGenerator;

public class HashTokenRegistryTest extends AbstractTokenRegistryTest {

    @Override
    protected TokenRegistry constructDefaultTokenRegistry() {
        return new HashTokenRegistry();
    }

    @Override
    protected TokenRegistry constructTokenRegistry(TokenGenerator generator) {
        return new HashTokenRegistry(generator);
    }

}
package nl.tehdreamteam.se42.web.token.impl;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.token.AbstractTokenRegistry;
import nl.tehdreamteam.se42.web.token.Token;
import nl.tehdreamteam.se42.web.token.generator.TokenGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class HashTokenRegistry extends AbstractTokenRegistry {

    private final Map<User, Token> tokens = new HashMap<>();

    public HashTokenRegistry() {
        super();
    }

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

        return getGenerator().generate(user);
    }

    @Override
    public void deregister(Token token) {
        token.invalidate();
        tokens.remove(token.getUser(), token);
    }

    @Override
    public Optional<Token> get(User user) {
        return Optional.ofNullable(tokens.get(user));
    }

}

package nl.tehdreamteam.se42.web.token.impl;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.token.Token;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Simple implementation of a {@link Token}.
 */
public class SimpleToken implements Token {

    private final AtomicBoolean valid;
    private final String id;
    private final User user;

    /**
     * Initializes this {@code Token} using the given {@code id} and {@link User}. By default this {@code Token} is
     * {@code valid}.
     *
     * @param id   The {@code id} of this {@code Token}.
     * @param user The {@code user} that this {@code Token} identifies.
     * @see Token#isValid()
     */
    public SimpleToken(String id, User user) {
        this.id = id;
        this.user = user;

        valid = new AtomicBoolean(true);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public boolean isValid() {
        return valid.get();
    }

    @Override
    public void invalidate() {
        valid.set(false);
    }

}

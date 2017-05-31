package nl.tehdreamteam.se42.web.token;

import nl.tehdreamteam.se42.domain.User;

/**
 * A {@code Token} can be used to identify a {@link User}.
 */
public interface Token {

    /**
     * Gets the {@code identifier} of this {@code Token}.
     *
     * @return A {@link String} containing the {@code identifier} for this {@code Token}.
     */
    String getId();

    /**
     * Gets the {@link User} that this token identifies.
     *
     * @return The {@code User} that this token identifies.
     */
    User getUser();

    /**
     * Checks whether this {@code Token} is {@code valid}. If a {@code Token} is {@code invalid}, it should not, in any
     * way, be considered useful for identification.
     *
     * @return True if, and only if, this {@code Token} is {@code valid}.
     */
    boolean isValid();

    /**
     * Invalidates this {@code Token} so that it no longer can be considered useful for identification.
     *
     * @see #isValid()
     */
    void invalidate();

}

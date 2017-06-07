package nl.tehdreamteam.se42.web.token;

import nl.tehdreamteam.se42.web.token.impl.HashTokenRegistry;

/**
 * Holds a single instance of a {@link TokenRegistry}.
 */
public class TokenRegistryFactory {

    private static TokenRegistry registry = new HashTokenRegistry();

    /**
     * Gets the global {@link TokenRegistry}.
     *
     * @return A {@code TokenRegistry}.
     */
    public static TokenRegistry getTokenRegistry() {
        return registry;
    }

    /**
     * Sets the global {@link TokenRegistry}.
     *
     * @param registry The new {@code registry} to set the global {@code TokenRegistry} to.
     */
    public static void setTokenRegistry(TokenRegistry registry) {
        TokenRegistryFactory.registry = registry;
    }

}

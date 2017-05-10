package nl.tehdreamteam.se42.web;

/**
 * A {@code Service} serves content to an external EndPoint. For example, a REST-{@code Service} would serve
 * REST-responses and receive REST-requests.
 */
public interface Service {

    /**
     * Starts this {@code Service}.
     */
    void start();

    /**
     * Stops this {@code Service}.
     */
    void stop();

    /**
     * Checks whether this {@code Service} is active or not. A {@code Service} is considered active if it can receive
     * requests and serve responses in its current state.
     *
     * @return True if, and only if, this {@code Service} is currently active.
     */
    boolean isActive();

}

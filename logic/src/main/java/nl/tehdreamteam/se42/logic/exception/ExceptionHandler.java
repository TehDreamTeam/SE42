package nl.tehdreamteam.se42.logic.exception;

/**
 * The {@code ExceptionHandler} handles a {@code Exception} and converts it into a {@code ServerError} object.
 *
 * @author Oscar de Leeuw
 */
@FunctionalInterface
public interface ExceptionHandler {

    /**
     * Handles an {@code Exception} and converts into into a {@code ServerError}.
     *
     * @param e The {@code Exception} that has been thrown.
     * @return A {@code ServerError} that represents the {@code Exception}.
     */
    ServerError handle(Exception e);
}

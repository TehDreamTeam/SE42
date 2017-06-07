package nl.tehdreamteam.se42.logic.exception;

import java.util.Optional;

/**
 * The {@code ServerError} represents an error that occurred within the server.
 *
 * @author Oscar de Leeuw
 */
public final class ServerError {

    private Throwable cause;
    private String message;
    private int code;

    /**
     * Creates a new {@code ServerError} object.
     *
     * @param cause   The cause of the {@code ServerError}.
     * @param message The message of the {@code ServerError}.
     * @param code    The code of the {@code ServerError}.
     */
    public ServerError(Throwable cause, String message, int code) {
        this.cause = cause;
        this.message = message;
        this.code = code;
    }

    /**
     * Creates a new {@code ServerError} object.
     *
     * @param message The message of the {@code ServerError}.
     * @param code    The code of the {@code ServerError}.
     */
    public ServerError(String message, int code) {
        this(null, message, code);
    }

    /**
     * Creates a new {@code ServerError} object.
     *
     * @param error The {@code ErrorType} of this {@code ServerError}.
     */
    public ServerError(ErrorType error) {
        this(null, error.message(), error.code());
    }

    /**
     * Creates a new {@code ServerError} object.
     *
     * @param cause the cause of the {@code ServerError}.
     * @param error The {@code ErrorType} of this {@code ServerError}.
     */
    public ServerError(Throwable cause, ErrorType error) {
        this(cause, error.message(), error.code());
    }

    public Optional<Throwable> getCause() {
        return Optional.ofNullable(cause);
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}

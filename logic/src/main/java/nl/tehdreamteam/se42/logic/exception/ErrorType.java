package nl.tehdreamteam.se42.logic.exception;

/**
 * {@code ErrorType} defines a set of predetermined types of errors that can be used to generate a {@code ServerError}.
 *
 * @author Oscar de Leeuw
 */
public enum ErrorType {
    INVALID_PASSWORD("Incorrect username or password.", 401),
    EMPTY_VALUE("Value is null or empty", 500);

    private String message;
    private int code;

    ErrorType(String message, int code) {
        this.message = message;
        this.code = code;
    }

    /**
     * Gets the message of the error.
     *
     * @return A String
     */
    public String message() {
        return message;
    }

    /**
     * Gets the code of the error.
     *
     * @return An integer.
     */
    public int code() {
        return code;
    }
}

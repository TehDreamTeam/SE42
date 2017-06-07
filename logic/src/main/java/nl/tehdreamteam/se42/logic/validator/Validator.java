package nl.tehdreamteam.se42.logic.validator;

import nl.tehdreamteam.se42.logic.exception.ServerError;

/**
 * A {@code Validator} can validate a certain object and return a {@link ServerError} when a validation fails.
 * The {@code Validator} class provides a common abstract platform for the implementation of {@code Validator}.
 *
 * @author Oscar de Leeuw
 */
public abstract class Validator {

    private ServerError error;

    protected Validator(ServerError error) {
        this.error = error;
    }

    public ServerError getError() {
        return error;
    }
}

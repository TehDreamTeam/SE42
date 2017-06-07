package nl.tehdreamteam.se42.logic.validator.input;

import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.validator.Validator;

import java.util.function.Supplier;

/**
 * {@code InputValidator} is a sort of validator for input.
 * It does not require an object at validation time in order to validate the validation function.
 *
 * @author Oscar de Leeuw
 */
public abstract class InputValidator extends Validator {

    protected InputValidator(ServerError error, int priority) {
        super(error, priority);
    }

    protected InputValidator(ServerError error, Priority priority) {
        super(error, priority);
    }

    protected abstract Supplier<Boolean> getValidateFunction();

    /**
     * Validates the validation function.
     * Will return it's {@code ServerError} when the validation function evaluates to false.
     *
     * @return A {@code ServerError} when the validation function fails. {@code Null} when the function passes.
     */
    public ServerError validate() {
        if (!getValidateFunction().get()) {
            return getError();
        }

        return null;
    }
}

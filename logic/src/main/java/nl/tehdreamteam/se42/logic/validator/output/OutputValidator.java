package nl.tehdreamteam.se42.logic.validator.output;

import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.validator.Validator;

import java.util.function.Predicate;

/**
 * {@code OutputValidator} is an validator for output.
 * It requires an object at validation time in order to evaluate the validation function.
 *
 * @param <R> The type of the {@code Object} that should be used for validation.
 * @author Oscar de Leeuw
 */
public abstract class OutputValidator<R> extends Validator {

    protected OutputValidator(ServerError error, int priority) {
        super(error, priority);
    }

    protected OutputValidator(ServerError error, Priority priority) {
        super(error, priority);
    }

    protected abstract Predicate<R> getPredicate();

    /**
     * Evaluates the validation function. Will return a {@code ServerError} when the function evaluates to false.
     *
     * @param value The value with which the validation function should be tested.
     * @return A {@code ServerError} when the validation function fails. {@code Null} if the function passes.
     */
    public ServerError validate(R value) {
        if (!getPredicate().test(value)) {
            return getError();
        }

        return null;
    }
}

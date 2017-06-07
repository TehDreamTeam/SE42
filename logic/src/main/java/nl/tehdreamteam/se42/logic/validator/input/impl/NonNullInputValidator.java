package nl.tehdreamteam.se42.logic.validator.input.impl;

import nl.tehdreamteam.se42.logic.exception.ErrorType;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.validator.input.InputValidator;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * An {@code InputValidator} that checks whether the given object is not null.
 *
 * @author Oscar de Leeuw
 */
public class NonNullInputValidator extends InputValidator {

    private Object object;

    /**
     * Creates a new {@code NonNullInputValidator}.
     *
     * @param error  The {@code ServerError} that should be returned when the validation fails.
     * @param object The object that should be evaluated.
     */
    public NonNullInputValidator(ServerError error, Object object) {
        super(error);
        this.object = object;
    }

    /**
     * Creates a new {@code NonNullInputValidator} with an {@link ErrorType#EMPTY_VALUE} error.
     *
     * @param object The object that should be evaluated.
     */
    public NonNullInputValidator(Object object) {
        this(new ServerError(ErrorType.EMPTY_VALUE), object);
    }

    @Override
    protected Supplier<Boolean> getValidateFunction() {
        return () -> Objects.nonNull(object);
    }
}

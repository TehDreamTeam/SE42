package nl.tehdreamteam.se42.logic.validator.input.impl;

import nl.tehdreamteam.se42.logic.exception.ErrorType;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.validator.input.InputValidator;

import java.util.function.Supplier;

/**
 * The {@code NotEmptyStringInputValidator} checks whether a {@code String} is not empty and does not contain only whitespaces.
 *
 * @author Oscar de Leeuw
 */
public class NotEmptyStringInputValidator extends InputValidator {

    private String checkedValue;

    /**
     * Creates a new {@code NotEmptyStringInputValidator}.
     *
     * @param error        The {@code ServerError} that should be returned when the validation fails.
     * @param checkedValue The {@code String} that should be checked.
     */
    public NotEmptyStringInputValidator(ServerError error, String checkedValue) {
        super(error, Priority.NORMAL);
        this.checkedValue = checkedValue;
    }

    /**
     * Creates a new {@code NotEmptyStringInputValidator}.
     * Will return a {@link ErrorType#EMPTY_VALUE} {@code ServerError}.
     *
     * @param checkedValue The {@code String} that should be checked.
     */
    public NotEmptyStringInputValidator(String checkedValue) {
        this(new ServerError(ErrorType.EMPTY_VALUE), checkedValue);
    }

    @Override
    public Supplier<Boolean> getValidateFunction() {
        return () -> !checkedValue.trim().isEmpty();
    }

}

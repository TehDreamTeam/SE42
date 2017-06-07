package nl.tehdreamteam.se42.logic.validator.output.impl;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.logic.exception.ErrorType;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.validator.output.OutputValidator;

import java.util.function.Predicate;

/**
 * {@code PasswordOutputValidator} validates the password of an {@link User}.
 *
 * @author Oscar de Leeuw
 */
public class PasswordOutputValidator extends OutputValidator<User> {

    private String password;

    /**
     * Creates a new {@code PasswordOutputValidator}.
     *
     * @param error    The {@code ServerError} that should be returned if the validation fails.
     * @param password The password that should match the {@code User's} password.
     */
    public PasswordOutputValidator(ServerError error, String password) {
        super(error);
        this.password = password;
    }

    /**
     * Creates a new {@code PasswordOutputValidator} with a {@link ErrorType#INVALID_PASSWORD} error.
     *
     * @param password The password that should match the {@code User's} password.
     */
    public PasswordOutputValidator(String password) {
        this(new ServerError(ErrorType.INVALID_PASSWORD), password);
    }

    @Override
    protected Predicate<User> getPredicate() {
        return pred -> pred.getLoginCredentials().getPassword().equals(password);
    }
}

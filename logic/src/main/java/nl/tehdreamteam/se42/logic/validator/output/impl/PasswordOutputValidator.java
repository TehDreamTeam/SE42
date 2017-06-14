package nl.tehdreamteam.se42.logic.validator.output.impl;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.logic.exception.ErrorType;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.security.Encryption;
import nl.tehdreamteam.se42.logic.validator.output.OutputValidator;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * {@code PasswordOutputValidator} validates the password of an {@link User}.
 *
 * @author Oscar de Leeuw
 */
public class PasswordOutputValidator extends OutputValidator<User> {

    private Encryption encryption;
    private String password;

    /**
     * Creates a new {@code PasswordOutputValidator}.
     *
     * @param error      The {@code ServerError} that should be returned if the validation fails.
     * @param encryption The {@code encryption} used to validate the password.
     * @param password   The password that should match the {@code User's} password.
     */
    public PasswordOutputValidator(ServerError error, Encryption<?> encryption, String password) {
        super(error, Priority.NORMAL);

        this.encryption = encryption;
        this.password = password;
    }

    /**
     * Creates a new {@code PasswordOutputValidator} with a {@link ErrorType#INVALID_PASSWORD} error.
     *
     * @param encryption The {@code encryption} used to validate the password.
     * @param password   The password that should match the {@code User's} password.
     */
    public PasswordOutputValidator(Encryption<?> encryption, String password) {
        this(new ServerError(ErrorType.INVALID_PASSWORD), encryption, password);
    }

    @Override
    protected Predicate<User> getPredicate() {
        return user -> {
            byte[] data = user.getLoginCredentials().getPassword().getBytes(StandardCharsets.UTF_8);
            String decrypted = new String(encryption.decrypt(Base64.getDecoder().decode(data)), StandardCharsets.UTF_8);

            return Objects.equals(password, decrypted);
        };
    }
}

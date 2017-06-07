package nl.tehdreamteam.se42.logic.validator.output.impl;

import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * @author Oscar de Leeuw
 */
public class PasswordOutputValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private PasswordOutputValidator validator;
    private ServerError error;
    private User user;
    private String password = "correct";

    @Before
    public void setUp() throws Exception {
        error = new ServerError("henk", 1);
        user = new User(new LoginCredentials("Henk", password));
    }

    @Test
    public void validate_withInvalidPassword_returnsError() throws Exception {
        makeValidatorWithInvalidPassword();
        checkValidationFails();
    }

    @Test
    public void validate_withValidPassword_returnsNull() throws Exception {
        makeValidatorWithValidPassword();
        checkValidationSucceeds();
    }

    @Test
    public void validate_withNullPassword_throwsException() throws Exception {
        makeValidatorWithNullPassword();
        checkValidationFails();
    }

    private void checkValidationFails() {
        ServerError actual = validator.validate(user);
        ServerError expected = error;

        assertSame(expected, actual);
    }

    private void checkValidationSucceeds() {
        ServerError actual = validator.validate(user);

        assertNull(actual);
    }

    private void makeValidatorWithNullPassword() {
        validator = new PasswordOutputValidator(error, null);
    }

    private void makeValidatorWithInvalidPassword() {
        validator = new PasswordOutputValidator(error, "wrong");
    }

    private void makeValidatorWithValidPassword() {
        validator = new PasswordOutputValidator(error, password);
    }

}
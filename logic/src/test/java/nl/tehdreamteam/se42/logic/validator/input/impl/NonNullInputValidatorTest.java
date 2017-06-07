package nl.tehdreamteam.se42.logic.validator.input.impl;

import nl.tehdreamteam.se42.logic.exception.ServerError;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * @author Oscar de Leeuw
 */
public class NonNullInputValidatorTest {

    private NonNullInputValidator validator;
    private ServerError error;

    @Before
    public void setUp() throws Exception {
        error = new ServerError("henk", 1);
    }

    @Test
    public void validate_withNull_returnsError() throws Exception {
        makeValidatorWithNullInput();
        checkValidationsFails();
    }

    @Test
    public void validate_withNonNull_returnsNull() throws Exception {
        makeValidatorWithNonNullInput();
        checkValidationSucceeds();
    }

    private void checkValidationsFails() {
        ServerError actual = validator.validate();
        ServerError expected = error;

        assertSame(expected, actual);
    }

    private void checkValidationSucceeds() {
        ServerError actual = validator.validate();

        assertNull(actual);
    }

    private void makeValidatorWithNullInput() {
        validator = new NonNullInputValidator(error, null);
    }

    private void makeValidatorWithNonNullInput() {
        validator = new NonNullInputValidator(error, "");
    }
}
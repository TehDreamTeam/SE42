package nl.tehdreamteam.se42.logic.validator.input.impl;

import nl.tehdreamteam.se42.logic.exception.ServerError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Oscar de Leeuw
 */
public class NotEmptyStringInputValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private NotEmptyStringInputValidator validator;
    private ServerError error;

    @Before
    public void setUp() throws Exception {
        error = new ServerError("Empty", 1);
    }

    @Test
    public void validate_withEmptyString_returnsError() throws Exception {
        makeValidatorWithEmptyString();
        checkValidationFails();
    }

    @Test
    public void validate_withNullString_throwsException() throws Exception {
        expectedException.expect(NullPointerException.class);
        makeValidatorWithNullString();
        checkValidationFails();
    }

    @Test
    public void validate_withValidString_returnsNull() throws Exception {
        makeValidatorWithValidString();
        checkValidationSucceeds();
    }

    private void makeValidatorWithEmptyString() {
        validator = new NotEmptyStringInputValidator(error, "");
    }

    private void makeValidatorWithNullString() {
        validator = new NotEmptyStringInputValidator(error, null);
    }

    private void makeValidatorWithValidString() {
        validator = new NotEmptyStringInputValidator(error, "henk");
    }

    private void checkValidationFails() {
        ServerError actual = validator.validate();
        ServerError expected = error;

        Assert.assertSame(expected, actual);
    }

    private void checkValidationSucceeds() {
        ServerError actual = validator.validate();

        Assert.assertNull(actual);
    }
}
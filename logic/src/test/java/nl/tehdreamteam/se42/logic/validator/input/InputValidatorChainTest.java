package nl.tehdreamteam.se42.logic.validator.input;

import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.validator.input.impl.NotEmptyStringInputValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

/**
 * @author Oscar de Leeuw
 */
public class InputValidatorChainTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private InputValidatorChain chain;
    private ServerError dudError = new ServerError("No returno", 500);

    @Before
    public void setUp() throws Exception {
        chain = new InputValidatorChain();
    }

    @Test
    public void validate_withError_returnsServerError() throws Exception {
        ServerError expected = new ServerError("Fatal error", 500);
        ServerError actual = chain.add(new NotEmptyStringInputValidator(dudError, "Kip"))
                .add(new NotEmptyStringInputValidator(expected, ""))
                .validate().get();

        Assert.assertSame(expected, actual);
    }

    @Test
    public void validate_withoutError_returnsNull() throws Exception {
        Optional<ServerError> actual = chain.add(new NotEmptyStringInputValidator(dudError, "kip"))
                .validate();

        Assert.assertFalse(actual.isPresent());
    }
}
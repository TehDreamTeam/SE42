package nl.tehdreamteam.se42.logic.validator.output;

import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.validator.output.impl.PasswordOutputValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

/**
 * @author Oscar de Leeuw
 */
public class OutputValidatorChainTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private OutputValidatorChain<User> chain;
    private ServerError dudError;
    private User user;
    private String password = "correct";

    @Before
    public void setUp() throws Exception {
        chain = new OutputValidatorChain<>();
        dudError = new ServerError("No returno", 500);
        user = new User(new LoginCredentials("Henk", password));
    }

    @Test
    public void validate_withError_returnsServerError() throws Exception {
        ServerError expected = new ServerError("Fatal error", 500);
        ServerError actual = chain.add(new PasswordOutputValidator(expected, "Kip"))
                .add(new PasswordOutputValidator(expected, ""))
                .validate(user).get();

        Assert.assertSame(expected, actual);
    }

    @Test
    public void validate_withoutError_returnsNull() throws Exception {
        Optional<ServerError> actual = chain.add(new PasswordOutputValidator(dudError, password))
                .validate(user);

        Assert.assertFalse(actual.isPresent());
    }

}
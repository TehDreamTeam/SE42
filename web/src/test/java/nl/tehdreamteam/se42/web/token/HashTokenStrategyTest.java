package nl.tehdreamteam.se42.web.token;

import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Oscar de Leeuw
 */
public class HashTokenStrategyTest {

    private User user;
    private TokenGenerationStrategy strategy;

    @Before
    public void setUp() throws Exception {
        user = new User(new LoginCredentials("Henk", "Wachtwoord1"));
        strategy = new HashTokenStrategy();
    }

    @Test
    public void generateToken_withStandardUser_returnsToken() throws Exception {
        String expected = "f2a6ea775e77c9f4fbfb3bdc038189c90fc212956c6a8778852b3190950de82c";
        String actual = strategy.generateToken(user);

        Assert.assertEquals(expected, actual);
    }
}
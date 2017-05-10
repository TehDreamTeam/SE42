package nl.tehdreamteam.se42.web.soap.user;

import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.controller.UserController;
import nl.tehdreamteam.se42.web.soap.SoapWebServiceConstants;
import nl.tehdreamteam.se42.web.token.TokenFactory;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of {@link UserController} that receives and serves SOAP messages.
 */
@WebService(targetNamespace = SoapWebServiceConstants.DEFAULT_NAMESPACE + "user")
public class SoapUserController implements UserController {

    private TokenFactory tokenFactory;

    /**
     * Creates a new {@code SoapUserController}.
     */
    public SoapUserController() {
        tokenFactory = new TokenFactory();
    }

    @Override
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        return tokenFactory.createToken(new User(new LoginCredentials(username, password))).getValue();
    }

    @Override
    public List<Long> getConversations(String token) {
        return Arrays.asList(1L, 2L, 4L);
    }

}

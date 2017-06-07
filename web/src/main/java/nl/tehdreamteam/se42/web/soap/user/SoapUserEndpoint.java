package nl.tehdreamteam.se42.web.soap.user;

import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.controller.UserController;
import nl.tehdreamteam.se42.web.soap.SoapWebServiceConstants;
import nl.tehdreamteam.se42.web.token.Token;
import nl.tehdreamteam.se42.web.token.TokenRegistry;
import nl.tehdreamteam.se42.web.token.impl.HashTokenRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of {@link UserController} that receives and serves SOAP messages.
 */
@WebService(targetNamespace = SoapWebServiceConstants.DEFAULT_NAMESPACE + "user")
public class SoapUserController implements UserController {

    private static final Logger logger = LogManager.getLogger(SoapUserController.class.getSimpleName());

    private final TokenRegistry registry;

    /**
     * Creates a new {@code SoapUserController}.
     */
    public SoapUserController() {
        registry = new HashTokenRegistry();
    }

    @Override
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        logger.debug("Soap request for login (username='{}', password='{}').", username, password);

        User user = new User(new LoginCredentials(username, password));
        Token token = registry.getOrRegister(user);

        return token.getId();
    }

    @Override
    public List<Long> getConversations(String token) {
        return Arrays.asList(1L, 2L, 4L);
    }

}

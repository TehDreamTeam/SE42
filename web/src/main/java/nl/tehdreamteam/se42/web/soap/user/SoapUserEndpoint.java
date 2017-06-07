package nl.tehdreamteam.se42.web.soap.user;

import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.endpoint.UserEndpoint;
import nl.tehdreamteam.se42.web.soap.SoapWebServiceConstants;
import nl.tehdreamteam.se42.web.token.Token;
import nl.tehdreamteam.se42.web.token.TokenRegistryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of {@link UserEndpoint} that receives and serves SOAP messages.
 */
@WebService(targetNamespace = SoapWebServiceConstants.DEFAULT_NAMESPACE + "user")
public class SoapUserEndpoint implements UserEndpoint {

    private static final Logger logger = LogManager.getLogger(SoapUserEndpoint.class.getSimpleName());

    @Override
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        logger.debug("Soap request for login (username='{}', password='{}').", username, password);

        User user = new User(new LoginCredentials(username, password));
        Token token = TokenRegistryFactory.getTokenRegistry().getOrRegister(user);

        return token.getId();
    }

    @Override
    public List<Long> getConversations(String token) {
        return Arrays.asList(1L, 2L, 4L);
    }

}

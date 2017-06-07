package nl.tehdreamteam.se42.web.soap.user;

import nl.tehdreamteam.se42.logic.controller.UserController;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.user.DefaultUserController;
import nl.tehdreamteam.se42.web.endpoint.UserEndpoint;
import nl.tehdreamteam.se42.web.soap.SoapWebServiceConstants;
import nl.tehdreamteam.se42.web.token.TokenRegistry;
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

    private final UserController controller;
    private final TokenRegistry registry;

    public SoapUserEndpoint(UserController controller, TokenRegistry registry) {
        this.controller = controller;
        this.registry = registry;
    }

    public SoapUserEndpoint() {
        this(new DefaultUserController(), TokenRegistryFactory.getTokenRegistry());
    }

    @Override
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        logger.debug("Soap request for login (username='{}', password='{}').", username, password);

        return controller.login(username, password)
                .match(ServerError::getMessage,
                        r -> registry.getOrRegister(r).getId());
    }

    @Override
    public long registerUser(String username, String password) {
        return 0;
    }

    @Override
    public List<Long> getConversations(String token) {


        return Arrays.asList(1L, 2L, 4L);
    }

}

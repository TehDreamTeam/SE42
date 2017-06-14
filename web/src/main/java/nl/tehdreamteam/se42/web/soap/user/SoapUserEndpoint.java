package nl.tehdreamteam.se42.web.soap.user;

import com.jnape.palatable.lambda.adt.Either;
import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.logic.controller.UserController;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.user.DefaultUserController;
import nl.tehdreamteam.se42.web.endpoint.UserEndpoint;
import nl.tehdreamteam.se42.web.soap.SoapWebServiceConstants;
import nl.tehdreamteam.se42.web.token.TokenRegistryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserEndpoint} that receives and serves SOAP messages.
 */
@WebService(targetNamespace = SoapWebServiceConstants.DEFAULT_NAMESPACE + "user")
public class SoapUserEndpoint implements UserEndpoint {

    private static final Logger logger = LogManager.getLogger(SoapUserEndpoint.class.getSimpleName());

    private final UserController controller = new DefaultUserController();

    @Override
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        logger.debug("Soap request for login (username='{}', password='{}').", username, password);

        Either<ServerError, User> result = controller.login(username, password);
        try {
            User user = result.biMapL(ServerError::getCause).orThrow(Optional::get);
            return TokenRegistryFactory.getTokenRegistry().getOrRegister(user).getId();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @Override
    public long registerUser(@WebParam(name = "username") String username,
                             @WebParam(name = "password") String password) {
        logger.debug("Soap request for register (username='{}', password='{}').", username, password);

        Either<ServerError, User> result = controller.register(username, password);
        try {
            User user = result.biMapL(ServerError::getCause).orThrow(Optional::get);
            return user.getId();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @Override
    public List<Long> getConversations(String token) {
        return Arrays.asList(1L, 2L, 4L);
    }

}

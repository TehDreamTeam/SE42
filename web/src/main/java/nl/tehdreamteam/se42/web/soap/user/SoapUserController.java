package nl.tehdreamteam.se42.web.soap.user;

import nl.tehdreamteam.se42.web.controller.UserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Implementation of {@link UserController} that receives and serves SOAP messages.
 */
@WebService
public class SoapUserController implements UserController {

    private static final Logger logger = LogManager.getLogger(SoapUserController.class.getSimpleName());

    @Override
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        logger.debug("Soap request for login (username='{}', password='{}').", username, password);

        return username + "-" + password;
    }

}

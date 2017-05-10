package nl.tehdreamteam.se42.web.soap.user;

import nl.tehdreamteam.se42.web.controller.UserController;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class SoapUserController implements UserController {

    @Override
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        return username + "-" + password;
    }

}

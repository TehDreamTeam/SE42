package nl.tehdreamteam.se42.logic.exception.impl;

import nl.tehdreamteam.se42.logic.exception.ErrorType;
import nl.tehdreamteam.se42.logic.exception.ExceptionHandler;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Oscar de Leeuw
 */
public class LoginExceptionHandlerTest {

    private ExceptionHandler handler = new LoginExceptionHandler();

    @Test
    public void handle_withException_returnsError() throws Exception {
        ServerError actual = handler.handle(new Exception());

        assertEquals(actual.getCode(), ErrorType.INVALID_PASSWORD.code());
    }
}
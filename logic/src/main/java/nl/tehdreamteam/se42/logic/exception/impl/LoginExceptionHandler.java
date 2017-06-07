package nl.tehdreamteam.se42.logic.exception.impl;

import nl.tehdreamteam.se42.logic.exception.ErrorType;
import nl.tehdreamteam.se42.logic.exception.ExceptionHandler;
import nl.tehdreamteam.se42.logic.exception.ServerError;

/**
 * An implementation of the {@code ExceptionHandler} that will return a {@link ErrorType#INVALID_PASSWORD} error upon any error.
 *
 * @author Oscar de Leeuw
 */
public class LoginExceptionHandler implements ExceptionHandler {
    @Override
    public ServerError handle(Exception e) {
        return new ServerError(ErrorType.INVALID_PASSWORD);
    }
}

package nl.tehdreamteam.se42.logic.exception.impl;

import nl.tehdreamteam.se42.logic.exception.ExceptionHandler;
import nl.tehdreamteam.se42.logic.exception.ServerError;

/**
 * Default implementation of {@code ExceptionHandler}.
 *
 * @author Oscar de Leeuw
 */
public class DefaultExceptionHandler implements ExceptionHandler {
    @Override
    public ServerError handle(Exception e) {
        return new ServerError(e, "Not supported yet", 500);
    }
}

package nl.tehdreamteam.se42.data.message;

import nl.tehdreamteam.se42.data.dao.DAO;
import nl.tehdreamteam.se42.domain.Message;

/**
 * Manages the data for the Message class.
 *
 * @author Oscar de Leeuw
 */
public interface MessageDAO extends DAO<Long, Message> {
}

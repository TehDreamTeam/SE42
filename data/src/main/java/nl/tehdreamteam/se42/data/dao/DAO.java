package nl.tehdreamteam.se42.data.dao;

import nl.tehdreamteam.se42.data.DataContract;

/**
 * DAO defines the standard operations that should be available for every specialized DAO.
 *
 * @param <I> The class that is used for identifying the object in the database.
 * @param <T> The class that is managed by this DAO.
 * @author Oscar de Leeuw
 */
public interface DAO<I, T> extends DataContract<I, T> {

}

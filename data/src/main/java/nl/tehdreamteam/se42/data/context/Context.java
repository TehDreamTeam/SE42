package nl.tehdreamteam.se42.data.context;

import nl.tehdreamteam.se42.data.DataContract;
import nl.tehdreamteam.se42.data.dao.DAO;

/**
 * A {@code Context} should wrap methods in a {@link DAO}, so that it provides convenient access.
 *
 * @param <I> The class that is used for identifying the object in the database.
 * @param <T> The class that is managed by this DAO.
 * @author Lesley
 */
public interface Context<I, T> extends DataContract<I, T> {

}

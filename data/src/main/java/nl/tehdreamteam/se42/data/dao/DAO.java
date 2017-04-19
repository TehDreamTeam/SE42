package nl.tehdreamteam.se42.data.dao;

import java.util.List;

/**
 * DAO defines the standard operations that should be available for every specialized DAO.
 *
 * @author Oscar de Leeuw
 * @param <ID> The class that is used for identifying the object in the database.
 * @param <T> The class that is managed by this DAO.
 */
public interface DAO<ID, T> {

    /**
     * Gets the count of the objects in the database.
     * @return An integer that represents the amount of objects in the database.
     */
    int count();

    /**
     * Creates a record in the database for the given object.
     * @param object The object that should be saved to the database.
     */
    void create(T object);

    /**
     * Edits a record of an object in the database.
     * @param object The object that should be updated in the database.
     */
    void edit(T object);

    /**
     * Finds an object in the database with the given id.
     * @param id The id of the object.
     * @return The object with the given id from the database.
     */
    T find(ID id);

    /**
     * Finds all the objects of this type in the database.
     * @return A List with all the objects.
     */
    List<T> findAll();

    /**
     * Removes the given object from the database.
     * @param object The object that should be removed from the database.
     */
    void remove(T object);
}

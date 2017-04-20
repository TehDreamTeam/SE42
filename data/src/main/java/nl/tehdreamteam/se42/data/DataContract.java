package nl.tehdreamteam.se42.data;

import java.util.List;

/**
 * A {@code DataContract} defines the standard operations that should be available for every specialized function
 * towards the database.
 *
 * @param <I> The type that is used for identifying an object in the database.
 * @param <T> The type that is managed by this {@code DataContract}.
 * @author Lesley
 */
public interface DataContract<I, T> {

    /**
     * Creates a record in the database for the given object.
     *
     * @param object The object that should be saved to the database.
     */
    void create(T object);

    /**
     * Edits a record of an object in the database.
     *
     * @param object The object that should be updated in the database.
     */
    void edit(T object);

    /**
     * Finds an object in the database with the given i.
     *
     * @param i The id of the object.
     * @return The object with the given i from the database.
     */
    T find(I i);

    /**
     * Finds all the objects of this type in the database.
     *
     * @return A List with all the objects.
     */
    List<T> findAll();

    /**
     * Removes the given object from the database.
     *
     * @param object The object that should be removed from the database.
     */
    void remove(T object);
}

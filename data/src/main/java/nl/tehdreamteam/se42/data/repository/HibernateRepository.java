package nl.tehdreamteam.se42.data.repository;

import nl.tehdreamteam.se42.data.DataConstants;
import nl.tehdreamteam.se42.data.dao.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Function;

/**
 * A {@code HibernateRepository} performs default operations, specified in a {@link DAO}, on a database
 * using hibernate {@link EntityTransaction Transactions}.
 *
 * @param <I> The type that is used for identifying an object in the database.
 * @param <T> The type that is managed by this {@code DataContract}.
 * @param <D> The type of the {@code DAO} that this {@code HibernateRepository} uses.
 * @author Lesley
 */
public abstract class HibernateRepository<I, T, D extends DAO<I, T>> {

    private final EntityManager manager;

    protected HibernateRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(DataConstants.HIBERNATE_PERSISTENCY_UNIT_NAME);
        manager = factory.createEntityManager();
    }

    /**
     * Creates a record in the database for the given object.
     *
     * @param object The object that should be saved to the database.
     */
    public void create(T object) {
        Function<D, Void> createFunction = dao -> {
            dao.create(object);
            return null;
        };

        performTransaction(createFunction);
    }

    /**
     * Edits a record of an object in the database.
     *
     * @param object The object that should be updated in the database.
     */
    public void edit(T object) {
        Function<D, Void> editFunction = dao -> {
            dao.edit(object);
            return null;
        };

        performTransaction(editFunction);
    }

    /**
     * Finds an object in the database with the given i.
     *
     * @param i The id of the object.
     * @return The object with the given i from the database.
     */
    public T find(I i) {
        Function<D, T> findFunction = dao -> dao.find(i);

        return performTransaction(findFunction);
    }

    /**
     * Finds all the objects of this type in the database.
     *
     * @return A List with all the objects.
     */
    public List<T> findAll() {
        Function<D, List<T>> findAllFunction = DAO::findAll;

        return performTransaction(findAllFunction);
    }

    /**
     * Removes the given object from the database.
     *
     * @param object The object that should be removed from the database.
     */
    public void remove(T object) {
        Function<D, Void> removeFunction = dao -> {
            dao.remove(object);
            return null;
        };

        performTransaction(removeFunction);
    }

    /**
     * Performs a {@link EntityTransaction} using a given function. This method performs the following steps, in order:
     * 1. Start a {@code EntityTransaction} (See {@link #beginTransaction()}.
     * 2. Create a {@link DAO} (See {@link #createDao(EntityManager)}.
     * 3. Execute the given {@code function}.
     * 4. Commit the {@code EntityTransaction} (See {@link #commitTransaction()}).
     *
     * @param function The {@code function} to execute in a {@code EntityTransaction}.
     * @param <R>      The return type of the given {@code function}.
     * @return An object fetched from the database, conform the requested type ({@code <R>}).
     */
    protected <R> R performTransaction(Function<D, R> function) {
        try {
            beginTransaction();

            D dao = createDao(manager);
            R value = function.apply(dao);

            commitTransaction();

            return value;
        } catch (Exception e) {
            rollbackTransaction();

            throw e;
        }
    }

    private void beginTransaction() {
        manager.getTransaction().begin();
    }

    private void commitTransaction() {
        manager.getTransaction().commit();
    }

    private void rollbackTransaction() {
        manager.getTransaction().rollback();
    }

    private void closeManager() {
        manager.close();
    }

    protected abstract D createDao(EntityManager manager);

}

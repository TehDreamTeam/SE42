package nl.tehdreamteam.se42.data.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Default implementation of the DAO interface.
 *
 * @param <I> The class of the identifier of the object.
 * @param <T> The class of the object whose persistence should be managed.
 * @author Oscar de Leeuw
 */
public abstract class HibernateDAO<I, T> implements DAO<I, T> {

    protected final EntityManager em;
    private final Class<T> type;

    /**
     * Creates a new HibernateDAO.
     *
     * @param entityManager The EntityManager for managing the JPA.
     * @param type          The type of the generic T.
     */
    protected HibernateDAO(EntityManager entityManager, Class<T> type) {
        this.em = entityManager;
        this.type = type;
    }

    @Override
    public void create(T object) {
        em.persist(object);
    }

    @Override
    public void edit(T object) {
        em.merge(object);
    }

    @Override
    public T find(I i) {
        return em.find(type, i);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(type));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public void remove(T object) {
        em.remove(object);
    }
}

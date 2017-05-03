package nl.tehdreamteam.se42.data;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Oscar de Leeuw
 */
public class EntityManagerLocator {

    private static EntityManager manager = Persistence.createEntityManagerFactory(DataConstants.HIBERNATE_PERSISTENCY_UNIT_NAME).createEntityManager();

    public static EntityManager getManager() {
        return manager;
    }

    public static void setEntityManager(EntityManager manager) {
        EntityManagerLocator.manager = manager;
    }
}

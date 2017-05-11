package nl.tehdreamteam.se42.web;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Keeps track of all {@link Service Services} and provides utility methods to execute on all {@code Services} at once.
 */
public final class ServiceContainer {

    private final Collection<Service> services = new ArrayList<>();

    /**
     * Starts all registered {@link Service Services}.
     */
    public void startAll() {
        services.forEach(Service::start);
    }

    /**
     * Stops all registered {@link Service Services}.
     */
    public void stopAll() {
        services.forEach(Service::stop);
    }

    /**
     * Registers a {@link Service} to this {@code ServiceContainer}.
     */
    public void addService(Service service) {
        services.add(service);
    }

    /**
     * Deregisters a {@link Service} from this {@code ServiceContainer}.
     */
    public void removeService(Service service) {
        services.remove(service);
    }

}

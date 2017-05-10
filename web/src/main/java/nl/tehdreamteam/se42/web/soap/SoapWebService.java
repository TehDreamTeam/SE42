package nl.tehdreamteam.se42.web.soap;

import nl.tehdreamteam.se42.web.Service;
import nl.tehdreamteam.se42.web.soap.user.SoapUserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static nl.tehdreamteam.se42.web.soap.SoapWebServiceConstants.DEFAULT_URL;

/**
 * Represents a {@link Service} that receives and serves SOAP messages.
 */
public class SoapWebService implements Service {

    private final Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private final AtomicBoolean started = new AtomicBoolean(false);

    private final List<Endpoint> endpoints = new ArrayList<>();

    @Override
    public void start() {
        if (isActive()) {
            throw new IllegalStateException("Soap service already started.");
        }

        registerEndpoints();
        started.set(true);

        logger.info("Soap service started on {}.", DEFAULT_URL);
    }

    @Override
    public void stop() {
        if (!isActive()) {
            throw new IllegalStateException("Soap service already stopped.");
        }

        stopEndpoints();
        started.set(false);

        logger.info("Soap service stopped on {}.", DEFAULT_URL);
    }

    private void registerEndpoints() {
        addEndpoint(DEFAULT_URL, new SoapUserController());
    }

    private void addEndpoint(String url, Object implementor) {
        Endpoint endpoint = Endpoint.create(implementor);
        endpoint.publish(url);

        endpoints.add(endpoint);

        logger.debug("Soap endpoint {} bound on {}.", implementor.getClass().getSimpleName(), url);
    }

    private void stopEndpoints() {
        endpoints.forEach(Endpoint::stop);
        endpoints.clear();
    }

    @Override
    public boolean isActive() {
        return started.get();
    }

}

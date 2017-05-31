package nl.tehdreamteam.se42.web.soap;

import nl.tehdreamteam.se42.web.soap.conversation.SoapConversationController;
import nl.tehdreamteam.se42.web.service.Service;
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

    private static final Logger logger = LogManager.getLogger(SoapWebService.class.getSimpleName());

    private final AtomicBoolean started = new AtomicBoolean(false);

    private final List<Endpoint> endpoints = new ArrayList<>();

    @Override
    public void start() {
        if (isActive()) {
            logger.debug("Soap service already started, ignoring start.");
            return;
        }

        started.set(true);

        try {
            registerEndpoints();
        } catch (Exception e) {
            stop();
            logger.error("Failed to register endpoints.", e);

            throw e;
        }

        logger.info("Soap service started on '{}'.", DEFAULT_URL);
    }

    @Override
    public void stop() {
        if (!isActive()) {
            logger.debug("Soap service already stopped, ignoring stop.");
            return;
        }

        started.set(false);
        stopEndpoints();

        logger.info("Soap service stopped on '{}'.", DEFAULT_URL);
    }

    private void registerEndpoints() {
        addEndpoint(DEFAULT_URL + "user", new SoapUserController());
        addEndpoint(DEFAULT_URL + "conversation", new SoapConversationController());
    }

    private void addEndpoint(String url, Object implementor) {
        Endpoint endpoint = Endpoint.create(implementor);
        endpoint.publish(url);

        endpoints.add(endpoint);

        logger.debug("Soap endpoint '{}' bound on '{}'.", implementor.getClass().getSimpleName(), url);
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

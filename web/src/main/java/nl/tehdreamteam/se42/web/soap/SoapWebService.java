package nl.tehdreamteam.se42.web.soap;

import nl.tehdreamteam.se42.web.Service;
import nl.tehdreamteam.se42.web.soap.conversation.SoapConversationController;
import nl.tehdreamteam.se42.web.soap.user.SoapUserController;

import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static nl.tehdreamteam.se42.web.soap.SoapWebServiceConstants.DEFAULT_URL;

/**
 * Represents a {@link Service} that receives and serves SOAP messages.
 */
public class SoapWebService implements Service {

    private final AtomicBoolean started = new AtomicBoolean(false);

    private final List<Endpoint> endpoints = new ArrayList<>();

    @Override
    public void start() {
        if (isActive()) {
            throw new IllegalStateException("Soap service already started.");
        }

        registerEndpoints();

        started.set(true);
    }

    @Override
    public void stop() {
        if (!isActive()) {
            throw new IllegalStateException("Soap service already stopped.");
        }

        stopEndpoints();

        started.set(false);
    }

    private void registerEndpoints() {
        addEndpoint(DEFAULT_URL + "user", new SoapUserController());
        addEndpoint(DEFAULT_URL + "conversation", new SoapConversationController());
    }

    private void addEndpoint(String url, Object implementor) {
        Endpoint endpoint = Endpoint.create(implementor);
        endpoint.publish(url);

        endpoints.add(endpoint);
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

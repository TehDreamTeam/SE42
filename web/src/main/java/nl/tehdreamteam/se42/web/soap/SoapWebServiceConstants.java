package nl.tehdreamteam.se42.web.soap;

import static nl.tehdreamteam.se42.web.service.ServiceConstants.DEFAULT_APPLICATION_NAME;

/**
 * Defines constants specifically used by the {@link SoapWebService}.
 */
public final class SoapWebServiceConstants {

    /**
     * Defines the {@code namespace} in which the {@link SoapWebService} operates.
     */
    public static final String DEFAULT_SOAP = "soap";

    /**
     * Defines on which {@code host} the {@link SoapWebService} will be bound.
     */
    public static final String DEFAULT_HOST = "http://localhost";

    /**
     * Defines on which {@code port} the {@link SoapWebService} will be bound.
     */
    public static final int DEFAULT_PORT = 8080;

    /**
     * Defines the fully qualified {@code url} on which the {@link SoapWebService} will be bound.
     */
    public static final String DEFAULT_URL = String.format("%s:%d/%s/%s/", DEFAULT_HOST, DEFAULT_PORT, DEFAULT_APPLICATION_NAME, DEFAULT_SOAP);

    /**
     * Defines the default namespace for a {@link SoapWebService}.
     */
    public static final String DEFAULT_NAMESPACE = "http://tehdreamteam.nl" + "/" + DEFAULT_APPLICATION_NAME + "/" + DEFAULT_SOAP + "/";

    private SoapWebServiceConstants() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }


}

package nl.tehdreamteam.se42.web;

import nl.tehdreamteam.se42.web.soap.SoapWebService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code Application} class gathers all services (Such as SOAP, REST, etc.), and starts them.
 */
public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class.getSimpleName());

    /**
     * Starts all services.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Service soap = new SoapWebService();

        try {
            soap.start();
        } catch (Exception e) {
            logger.fatal("Failed to start soap service.", e);
        }
    }

}

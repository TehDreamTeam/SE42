package nl.tehdreamteam.se42.web;

import nl.tehdreamteam.se42.web.soap.SoapWebService;

/**
 * The {@code Application} class gathers all services (Such as SOAP, REST, etc.), and starts them.
 */
public class Application {

    /**
     * Starts all services.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Service soap = new SoapWebService();
        soap.start();
    }

}

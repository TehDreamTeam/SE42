package nl.tehdreamteam.se42.web.soap;

import nl.tehdreamteam.se42.web.soap.user.SoapUserController;

import javax.xml.ws.Endpoint;

public class SoapWebService {

    private static final String url = "http://localhost:8080/se42/soap/";

    public static void main(String[] args) {
        Endpoint.publish(url, new SoapUserController());
    }

}

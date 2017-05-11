package nl.tehdreamteam.se42.web.soap;

import nl.tehdreamteam.se42.web.service.Service;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SoapWebServiceTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private Service service;

    @Test
    public void start_whenStopped_startsService() {
        givenSoapWebService();

        startService();

        verifyServiceIsActive();
    }

    @Test
    public void start_whenAlreadyStarted_ignoresCallAndRemainsActive() {
        givenSoapWebService();
        startService();

        startService();

        verifyServiceIsActive();
    }

    @Test
    public void stop_whenStarted_stopsService() {
        givenSoapWebService();
        startService();

        stopService();

        verifyServiceIsInactive();
    }

    @Test
    public void stop_whenAlreadyStopped_ignoresCallAndRemainsInactive() {
        givenSoapWebService();

        stopService();

        verifyServiceIsInactive();
    }

    @Before
    public void reset() {
        service = null;
    }

    @After
    public void closeService() {
        if (service != null && service.isActive()) {
            service.stop();
        }
    }

    private void verifyServiceIsActive() {
        verifyServiceIsActive(true);
    }

    private void verifyServiceIsInactive() {
        verifyServiceIsActive(false);
    }

    private void verifyServiceIsActive(boolean expected) {
        assertThat(service.isActive(), is(expected));
    }

    private void givenSoapWebService() {
        service = new SoapWebService();
    }

    private void startService() {
        service.start();
    }

    private void stopService() {
        service.stop();
    }

}
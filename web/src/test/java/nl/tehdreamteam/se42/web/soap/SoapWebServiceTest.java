package nl.tehdreamteam.se42.web.soap;

import nl.tehdreamteam.se42.web.Service;
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
    public void start_whenAlreadyStarted_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Soap service already started.");

        givenSoapWebService();
        startService();

        startService();
    }

    @Test
    public void stop_whenStarted_stopsService() {
        givenSoapWebService();
        startService();

        stopService();

        verifyServiceIsNotActive();
    }

    @Test
    public void stop_whenAlreadyStopped_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Soap service already stopped.");

        givenSoapWebService();

        stopService();
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

    private void verifyServiceIsNotActive() {
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
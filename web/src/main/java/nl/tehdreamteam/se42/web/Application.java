package nl.tehdreamteam.se42.web;

import nl.tehdreamteam.se42.web.command.CommandHandler;
import nl.tehdreamteam.se42.web.command.impl.ExitApplicationCommand;
import nl.tehdreamteam.se42.web.command.impl.StartServicesCommand;
import nl.tehdreamteam.se42.web.command.impl.StopServicesCommand;
import nl.tehdreamteam.se42.web.service.ServiceContainer;
import nl.tehdreamteam.se42.web.soap.SoapWebService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * The {@code Application} class gathers all services (Such as SOAP, REST, etc.), and starts them.
 */
public final class Application {

    private static final Logger logger = LogManager.getLogger(Application.class.getSimpleName());

    private final CommandHandler handler;
    private final ServiceContainer container;

    private Application() {
        container = new ServiceContainer();
        container.addService(new SoapWebService());
        container.startAll();

        handler = new CommandHandler();
        handler.register(new StartServicesCommand(container));
        handler.register(new StopServicesCommand(container));
        handler.register(new ExitApplicationCommand(container));
    }

    private void startReadingCLI() {
        try (Scanner sc = new Scanner(System.in)) {
            processScannerInput(sc);
        }
    }

    private void processScannerInput(Scanner sc) {
        String line;
        while ((line = sc.nextLine()) != null) {
            processReadLine(line);
        }
    }

    private void processReadLine(String line) {
        try {
            handler.handle(line);
        } catch (Exception e) {
            logger.error("Failed to execute command: {}.", e.getMessage());
        }
    }

    /**
     * Starts all services.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Application application = new Application();
        application.startReadingCLI();
    }

}

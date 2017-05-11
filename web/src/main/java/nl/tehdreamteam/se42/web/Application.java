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

    /**
     * Starts all services.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        ServiceContainer container = new ServiceContainer();
        container.addService(new SoapWebService());
        container.startAll();

        CommandHandler handler = new CommandHandler();
        handler.register(new StartServicesCommand(container));
        handler.register(new StopServicesCommand(container));
        handler.register(new ExitApplicationCommand(container));

        startReadingCommandLine(handler);
    }

    private static void startReadingCommandLine(CommandHandler handler) {
        try (Scanner sc = new Scanner(System.in)) {
            String line;
            while ((line = sc.nextLine()) != null) {
                try {
                    handler.handle(line);
                } catch (Exception e) {
                    logger.error("Failed to execute command: {}.", e.getMessage());
                }
            }
        }
    }

}

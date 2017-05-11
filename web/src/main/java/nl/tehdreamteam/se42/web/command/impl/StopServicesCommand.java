package nl.tehdreamteam.se42.web.command.impl;

import nl.tehdreamteam.se42.web.command.Command;
import nl.tehdreamteam.se42.web.command.CommandHandler;
import nl.tehdreamteam.se42.web.service.Service;
import nl.tehdreamteam.se42.web.service.ServiceContainer;

/**
 * A {@link Command} that stops all active {@link Service Services}.
 */
public class StopServicesCommand implements Command {

    private final ServiceContainer container;

    /**
     * Initializes this {@code ExitApplicationCommand} using the given arguments.
     *
     * @param container The {@link ServiceContainer} that will be used to stop all active {@link Service Services}.
     */
    public StopServicesCommand(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void execute(CommandHandler handler, String line, String[] args) {
        container.stopAll();
    }

    @Override
    public String getName() {
        return "Stop Services";
    }

    @Override
    public String getDescription() {
        return "Stops all active services.";
    }

    @Override
    public String[] getIdentifiers() {
        return new String[]{"stop"};
    }

}

package nl.tehdreamteam.se42.web.command.impl;

import nl.tehdreamteam.se42.web.Service;
import nl.tehdreamteam.se42.web.ServiceContainer;
import nl.tehdreamteam.se42.web.command.Command;
import nl.tehdreamteam.se42.web.command.CommandHandler;

/**
 * A {@link Command} that starts all inactive {@link Service Services}.
 */
public class StartServicesCommand extends Command {

    private final ServiceContainer container;

    /**
     * Initializes this {@code ExitApplicationCommand} using the given arguments.
     *
     * @param container The {@link ServiceContainer} that will be used to start all inactive {@link Service Services}.
     */
    public StartServicesCommand(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void execute(CommandHandler handler, String line, String[] args) {
        container.startAll();
    }

    @Override
    public String getName() {
        return "Start Services";
    }

    @Override
    public String getDescription() {
        return "Starts all inactive services.";
    }

    @Override
    public String[] getIdentifiers() {
        return new String[]{"start"};
    }
}

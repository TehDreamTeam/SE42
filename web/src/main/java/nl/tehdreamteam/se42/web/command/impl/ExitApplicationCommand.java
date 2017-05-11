package nl.tehdreamteam.se42.web.command.impl;

import nl.tehdreamteam.se42.web.Service;
import nl.tehdreamteam.se42.web.ServiceContainer;
import nl.tehdreamteam.se42.web.command.Command;
import nl.tehdreamteam.se42.web.command.CommandHandler;

/**
 * A {@link Command} that exits the Application.
 */
public class ExitApplicationCommand extends Command {

    private final ServiceContainer container;

    /**
     * Initializes this {@code ExitApplicationCommand} using the given arguments.
     *
     * @param container The {@link ServiceContainer} that will be used to stop all active {@link Service Services}.
     */
    public ExitApplicationCommand(ServiceContainer container) {
        this.container = container;
    }

    @Override
    public void execute(CommandHandler handler, String line, String[] args) {
        container.stopAll();

        System.exit(0);
    }

    @Override
    public String getName() {
        return "Stop Application";
    }

    @Override
    public String getDescription() {
        return "Firstly stops all active services, and then stops the Application.";
    }

    @Override
    public String[] getIdentifiers() {
        return new String[]{"exit"};
    }

}

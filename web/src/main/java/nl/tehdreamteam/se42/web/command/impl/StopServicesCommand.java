package nl.tehdreamteam.se42.web.command.impl;

import nl.tehdreamteam.se42.web.ServiceContainer;
import nl.tehdreamteam.se42.web.command.Command;
import nl.tehdreamteam.se42.web.command.CommandHandler;

public class StopServicesCommand extends Command {

    private final ServiceContainer container;

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

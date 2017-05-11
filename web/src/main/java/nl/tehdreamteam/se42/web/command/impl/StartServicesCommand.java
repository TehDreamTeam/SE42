package nl.tehdreamteam.se42.web.command.impl;

import nl.tehdreamteam.se42.web.ServiceContainer;
import nl.tehdreamteam.se42.web.command.Command;
import nl.tehdreamteam.se42.web.command.CommandHandler;

public class StartServicesCommand extends Command {

    private final ServiceContainer container;

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

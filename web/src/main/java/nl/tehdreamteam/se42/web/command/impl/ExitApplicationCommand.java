package nl.tehdreamteam.se42.web.command.impl;

import nl.tehdreamteam.se42.web.ServiceContainer;
import nl.tehdreamteam.se42.web.command.Command;
import nl.tehdreamteam.se42.web.command.CommandHandler;

public class ExitApplicationCommand extends Command {

    private final ServiceContainer container;

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

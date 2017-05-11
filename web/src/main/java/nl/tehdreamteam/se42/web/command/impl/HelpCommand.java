package nl.tehdreamteam.se42.web.command.impl;

import nl.tehdreamteam.se42.web.command.Command;
import nl.tehdreamteam.se42.web.command.CommandHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A {@link Command} that provides 'help' about other {@code Commands}.
 */
public class HelpCommand implements Command {

    private static final Logger logger = LogManager.getLogger(HelpCommand.class.getSimpleName());

    @Override
    public void execute(CommandHandler handler, String line, String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No command specified.");
        }

        Command command = handler.getCommand(args[0]);
        if (command == null) {
            throw new NullPointerException("Command not found.");
        }

        logger.info("{}: {}", command.getName(), command.getDescription());
    }

    @Override
    public String getName() {
        return "Help";
    }

    @Override
    public String getDescription() {
        return "Shows the description of a command.";
    }

    @Override
    public String[] getIdentifiers() {
        return new String[]{"help"};
    }

}

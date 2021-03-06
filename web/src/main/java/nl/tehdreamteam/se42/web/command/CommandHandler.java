package nl.tehdreamteam.se42.web.command;

import nl.tehdreamteam.se42.web.command.impl.HelpCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Handles {@link Command Commands} registered in this {@code CommandHandler}.
 */
public class CommandHandler {

    private static final Logger logger = LogManager.getLogger(CommandHandler.class.getSimpleName());
    private static final String COMMAND_ARGUMENT_SPLIT_TOKEN = " ";

    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Registers all default {@link Command Commands}.
     */
    public CommandHandler() {
        register(new HelpCommand());
    }

    /**
     * Handles a {@link Command} from the given input-{@code line}.
     *
     * @param line The input-{@code line}.
     */
    public void handle(String line) {
        Objects.requireNonNull(line);

        String identifier = parseIdentifierFromLine(line);
        String input = parseInputFromLine(line);
        String[] arguments = parseArgumentsFromLine(line);
        handle(identifier, input, arguments);
    }

    private String parseIdentifierFromLine(String line) {
        String[] splits = line.split(COMMAND_ARGUMENT_SPLIT_TOKEN);
        return splits[0];
    }

    private String parseInputFromLine(String line) {
        String[] splits = line.split(COMMAND_ARGUMENT_SPLIT_TOKEN);

        String identifier = parseIdentifierFromLine(line);
        return splits.length > 1 ? line.substring(identifier.length() + 1) : "";
    }

    private String[] parseArgumentsFromLine(String line) {
        String[] splits = line.split(COMMAND_ARGUMENT_SPLIT_TOKEN);

        return Arrays.copyOfRange(splits, 1, splits.length);
    }

    private void handle(String identifier, String input, String[] args) {
        Command command = getCommand(identifier);
        if (command == null) {
            throw new NullPointerException(String.format("Command with identifier '%s' not registered", identifier));
        }

        command.execute(this, input, args);

        logger.debug("Executed command '{}'.", command.getName());
    }

    /**
     * Finds a registered {@link Command}.
     *
     * @param identifier The {@code identifier} of the {@code Command} to find.
     * @return A {@code Command} that was found.
     */
    public Command getCommand(String identifier) {
        Objects.requireNonNull(identifier);

        return commands.get(identifier.toLowerCase());
    }

    /**
     * Registers the given {@link Command} in this {@code CommandHandler}.
     *
     * @param command The {@code Command} to register.
     */
    public void register(Command command) {
        verifyValidCommand(command);

        String[] identifiers = command.getIdentifiers();
        Arrays.stream(identifiers).forEach(i -> register(i, command));
    }

    private void register(String identifier, Command command) {
        commands.put(identifier.toLowerCase(), command);

        logger.debug("Command '{}' registered under identifier '{}'.", command.getName(), identifier);
    }

    /**
     * Deregisters the given {@link Command} from this {@code CommandHandler}.
     *
     * @param command The {@code Command} to deregister.
     */
    public void deregister(Command command) {
        verifyValidCommand(command);

        Arrays.stream(command.getIdentifiers()).forEach(i -> deregister(i, command));
    }

    private void deregister(String identifier, Command command) {
        commands.remove(identifier.toLowerCase(), command);

        logger.debug("Command '{}' deregistered under identifier '{}'.", command.getName(), identifier);
    }

    private void verifyValidCommand(Command command) {
        Objects.requireNonNull(command);

        String[] identifiers = command.getIdentifiers();
        if (identifiers == null || identifiers.length == 0) {
            throw new NullPointerException(String.format("Command '%s' has no identifiers", command.getName()));
        }
    }

}

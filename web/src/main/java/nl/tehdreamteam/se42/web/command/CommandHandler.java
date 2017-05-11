package nl.tehdreamteam.se42.web.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Handles {@link Command Commands} registered in this {@code CommandHandler}.
 */
public class CommandHandler {

    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Handles a {@link Command} from the given input-{@code line}.
     *
     * @param line The input-{@code line}.
     */
    public void handle(String line) {
        Objects.requireNonNull(line);

        String[] splits = line.split(" ");

        String identifier = splits[0];
        String input = splits.length > 1 ? line.substring(identifier.length() + 1) : "";
        String[] arguments = Arrays.copyOfRange(splits, 1, splits.length);
        handle(identifier, input, arguments);
    }

    private void handle(String identifier, String input, String[] args) {
        Command command = commands.get(identifier);
        if (command == null) {
            throw new NullPointerException("Command with the given identifier not registered.");
        }

        command.execute(this, input, args);
    }

    /**
     * Registers the given {@link Command} in this {@code CommandHandler}.
     *
     * @param command The {@code Command} to register.
     */
    public void register(Command command) {
        verifyValidCommand(command);

        Arrays.stream(command.getIdentifiers()).forEach(i -> register(i, command));
    }

    private void register(String identifier, Command command) {
        commands.put(identifier, command);
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
        commands.remove(identifier, command);
    }

    private void verifyValidCommand(Command command) {
        Objects.requireNonNull(command);

        String[] identifiers = command.getIdentifiers();
        if (identifiers == null || identifiers.length == 0) {
            throw new NullPointerException("This command has no identifiers.");
        }
    }

}

package nl.tehdreamteam.se42.web.command;

/**
 * Represents a single {@code Command} that can be executed.
 */
public abstract class Command {

    /**
     * Executes this {@code Command} using the given input.
     *
     * @param handler The {@link CommandHandler} in which this {@code Command} gets executed.
     * @param line    The full input-{@code line} that was executed (not including the {@code Command} itself).
     * @param args    The {@code arguments} of this {@code Command}.
     */
    public abstract void execute(CommandHandler handler, String line, String[] args);

    /**
     * Gets the {@code name} of this {@code Command}.
     *
     * @return The {@code name} of this {@code Command}.
     */
    public abstract String getName();

    /**
     * Gets the {@code description} of this {@code Command}.
     *
     * @return The {@code description} of this {@code Command}.
     */
    public abstract String getDescription();

    /**
     * Gets all {@code identifiers} of this {@code Command}.
     *
     * @return All {@code identifiers} of this {@code Command}.
     */
    public abstract String[] getIdentifiers();

}

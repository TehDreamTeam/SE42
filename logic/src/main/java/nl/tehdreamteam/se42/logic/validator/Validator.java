package nl.tehdreamteam.se42.logic.validator;

import nl.tehdreamteam.se42.logic.exception.ServerError;

/**
 * A {@code Validator} can validate a certain object and return a {@link ServerError} when a validation fails.
 * The {@code Validator} class provides a common abstract platform for the implementation of {@code Validator}.
 *
 * @author Oscar de Leeuw
 */
public abstract class Validator implements Comparable<Validator> {

    private final ServerError error;
    private final int priority;

    protected Validator(ServerError error, int priority) {
        this.error = error;
        this.priority = priority;
    }

    protected Validator(ServerError error, Priority priority) {
        this(error, priority.value());
    }

    public ServerError getError() {
        return error;
    }

    @Override
    public int compareTo(Validator o) {
        return Integer.compare(priority, o.priority);
    }

    /**
     * Enum for determining the priority of a validator.
     */
    public enum Priority {
        LOWEST(Integer.MAX_VALUE), LOW(1), NORMAL(0), HIGH(-1), HIGHEST(Integer.MIN_VALUE);

        private int value;

        Priority(int value) {
            this.value = value;
        }

        /**
         * Gets the value of the enum.
         *
         * @return The value.
         */
        public int value() {
            return value;
        }
    }
}

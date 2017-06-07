package nl.tehdreamteam.se42.logic.validator;

import java.util.Collection;
import java.util.HashSet;

/**
 * A {@code ValidatorChain} is a chain of {@link Validator Validators} that can be used to validate a certain object.
 *
 * @param <T> The type of the implementation of the {@code ValidatorChain}.
 * @param <V> The type of the implementation of the {@code Validator}.
 * @author Oscar de Leeuw
 */
public abstract class ValidatorChain<T extends ValidatorChain, V extends Validator> {

    protected Collection<V> validators;

    /**
     * Creates a new {@code ValidatorChain} with a {@code HashSet}.
     */
    public ValidatorChain() {
        validators = new HashSet<>();
    }

    /**
     * Adds a new {@code Validator} to the chain.
     *
     * @param validator The {@code Validator} that should be added to the chain.
     * @return The current {@code ValidatorChain}.
     */
    public T add(V validator) {
        validators.add(validator);
        return (T) this;
    }
}

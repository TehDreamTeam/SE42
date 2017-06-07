package nl.tehdreamteam.se42.logic.validator.output;

import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.validator.ValidatorChain;

import java.util.Objects;
import java.util.Optional;

/**
 * {@code OutputValidatorChain} is a chain of {@code OutputValidators}.
 *
 * @param <R> The type of the object that will be used to validate the chain.
 * @author Oscar de Leeuw
 */
public class OutputValidatorChain<R> extends ValidatorChain<OutputValidatorChain<R>, OutputValidator<R>> {

    /**
     * Validates all the validators that are currently chained in the {@code ValidatorChain}.
     *
     * @param value The value that should be used to validate all the validators in the chain.
     * @return An optional with the first {@code ServerError} that was encountered.
     */
    public Optional<ServerError> validate(R value) {
        return validators.stream()
                .map(r -> r.validate(value))
                .filter(Objects::nonNull)
                .findFirst();   //TODO SHOULD EVALUATE THE RIGHT WAY.
        // does not actually return the first one in the collection.
    }
}

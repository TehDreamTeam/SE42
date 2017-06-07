package nl.tehdreamteam.se42.logic.validator.input;

import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.validator.ValidatorChain;

import java.util.Objects;
import java.util.Optional;

/**
 * {@code InputValidatorChain} is a {@code ValidatorChain} of {@code InputValidators}.
 *
 * @author Oscar de Leeuw
 */
public class InputValidatorChain extends ValidatorChain<InputValidatorChain, InputValidator> {

    /**
     * Validates all the validators that are currently chained in the {@code ValidatorChain}.
     *
     * @return An optional with the first {@code ServerError} that was encountered.
     */
    public Optional<ServerError> validate() {
        return validators.stream().map(InputValidator::validate).filter(Objects::nonNull).findFirst();
    }
}

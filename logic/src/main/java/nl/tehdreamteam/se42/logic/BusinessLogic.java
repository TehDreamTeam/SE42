package nl.tehdreamteam.se42.logic;

import com.jnape.palatable.lambda.adt.Either;
import nl.tehdreamteam.se42.logic.exception.ExceptionHandler;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.exception.impl.DefaultExceptionHandler;
import nl.tehdreamteam.se42.logic.validator.input.InputValidatorChain;
import nl.tehdreamteam.se42.logic.validator.output.OutputValidatorChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@code BusinessLogic} executes all logic in the logic layer.
 * {@code BusinessLogic} supports input validation before a data request and post data request validation.
 * It also supports custom database exception handling through the {@link ExceptionHandler} interface.
 *
 * @author Oscar de Leeuw
 */
public class BusinessLogic {

    private static Logger logger = LogManager.getLogger(BusinessLogic.class);

    private BusinessLogic() {
        throw new UnsupportedOperationException("Should not be initiated");
    }

    /**
     * Executes all the business logic.
     * It will first run the input validation, then execute the data retrieval function and lastly validate the retrieved data.
     * Any exception that occurs during the data retrieval will be caught and given to the supplied {@code ExceptionHandler}.
     * Any exception that is thrown by the validation functions will not be caught.
     *
     * @param retrievalFunction The {@code Supplier} for fetching the data from the data layer.
     * @param inputValidation   A {@code InputValidatorChain} of {@link nl.tehdreamteam.se42.logic.validator.input.InputValidator InputValidators} that should validated.
     * @param outputValidation  A {@code OutputValidatorChain} of {@link nl.tehdreamteam.se42.logic.validator.output.OutputValidator OutputValidators} that should validated.
     * @param exceptionHandler  A {@code ExceptionHandler} that will be used to handle any exception that might be raised by the data layer.
     * @param <T>               The type of the data that should be retrieved and validated.
     * @return The result of the {@code retrievalFunction}.
     */
    public static <T> Either<ServerError, T> execute(Supplier<T> retrievalFunction, InputValidatorChain inputValidation, OutputValidatorChain<T> outputValidation, ExceptionHandler exceptionHandler) {
        Either<ServerError, T> ret;

        Optional<ServerError> inputError = inputValidation.validate();
        if (inputError.isPresent()) {
            ret = Either.left(inputError.get());

            logger.info("Input validation failed with error", inputError.get());

            return ret;
        }

        ret = Either.trying(retrievalFunction::get, exceptionHandler::handle);

        if (!ret.toOptional().isPresent() && ret.forfeit(e -> null) == null) { //Case for when there is a null right value.
            throw new IllegalStateException("Retrieved value should not be null.");
        }

        if (ret.projectA().isPresent()) {
            logger.info("Database error occurred.", ret.projectA().get());

            return ret;
        }

        Optional<ServerError> outputError = outputValidation.validate(ret.orThrow(e -> new IllegalStateException("Retrieved value should not be null.")));

        if (outputError.isPresent()) {

            logger.info("Output validation failed with error.", outputError.get());

            ret = Either.left(outputError.get());
        }

        return ret;
    }

    /**
     * See {@link #execute(Supplier, InputValidatorChain, OutputValidatorChain, ExceptionHandler)}
     *
     * @param retrievalFunction The {@code Supplier} for fetching the data from the data layer.
     * @param inputValidation   A {@code InputValidatorChain} of {@link nl.tehdreamteam.se42.logic.validator.input.InputValidator InputValidators} that should validated.
     * @param outputValidation  A {@code OutputValidatorChain} of {@link nl.tehdreamteam.se42.logic.validator.output.OutputValidator OutputValidators} that should validated.
     * @param <T>               The type of the data that should be retrieved and validated.
     * @return The result of the {@code retrievalFunction}.
     */
    public static <T> Either<ServerError, T> execute(Supplier<T> retrievalFunction, InputValidatorChain inputValidation, OutputValidatorChain<T> outputValidation) {
        return execute(retrievalFunction, inputValidation, outputValidation, new DefaultExceptionHandler());
    }

    /**
     * See {@link #execute(Supplier, InputValidatorChain, OutputValidatorChain, ExceptionHandler)}
     *
     * @param retrievalFunction The {@code Supplier} for fetching the data from the data layer.
     * @param outputValidation  A {@code OutputValidatorChain} of {@link nl.tehdreamteam.se42.logic.validator.output.OutputValidator OutputValidators} that should validated.
     * @param exceptionHandler  A {@code ExceptionHandler} that will be used to handle any exception that might be raised by the data layer.
     * @param <T>               The type of the data that should be retrieved and validated.
     * @return The result of the {@code retrievalFunction}.
     */
    public static <T> Either<ServerError, T> execute(Supplier<T> retrievalFunction, OutputValidatorChain<T> outputValidation, ExceptionHandler exceptionHandler) {
        return execute(retrievalFunction, new InputValidatorChain(), outputValidation, exceptionHandler);
    }

    /**
     * See {@link #execute(Supplier, InputValidatorChain, OutputValidatorChain, ExceptionHandler)}
     *
     * @param retrievalFunction The {@code Supplier} for fetching the data from the data layer.
     * @param outputValidation  A {@code OutputValidatorChain} of {@link nl.tehdreamteam.se42.logic.validator.output.OutputValidator OutputValidators} that should validated.
     * @param <T>               The type of the data that should be retrieved and validated.
     * @return The result of the {@code retrievalFunction}.
     */
    public static <T> Either<ServerError, T> execute(Supplier<T> retrievalFunction, OutputValidatorChain<T> outputValidation) {
        return execute(retrievalFunction, outputValidation, new DefaultExceptionHandler());
    }

    /**
     * See {@link #execute(Supplier, InputValidatorChain, OutputValidatorChain, ExceptionHandler)}
     *
     * @param retrievalFunction The {@code Supplier} for fetching the data from the data layer.
     * @param inputValidation   A {@code InputValidatorChain} of {@link nl.tehdreamteam.se42.logic.validator.input.InputValidator InputValidators} that should validated.
     * @param exceptionHandler  A {@code ExceptionHandler} that will be used to handle any exception that might be raised by the data layer.
     * @param <T>               The type of the data that should be retrieved and validated.
     * @return The result of the {@code retrievalFunction}.
     */
    public static <T> Either<ServerError, T> execute(Supplier<T> retrievalFunction, InputValidatorChain inputValidation, ExceptionHandler exceptionHandler) {
        return execute(retrievalFunction, inputValidation, new OutputValidatorChain<>(), exceptionHandler);
    }

    /**
     * See {@link #execute(Supplier, InputValidatorChain, OutputValidatorChain, ExceptionHandler)}
     *
     * @param retrievalFunction The {@code Supplier} for fetching the data from the data layer.
     * @param inputValidation   A {@code InputValidatorChain} of {@link nl.tehdreamteam.se42.logic.validator.input.InputValidator InputValidators} that should validated.
     * @param <T>               The type of the data that should be retrieved and validated.
     * @return The result of the {@code retrievalFunction}.
     */
    public static <T> Either<ServerError, T> execute(Supplier<T> retrievalFunction, InputValidatorChain inputValidation) {
        return execute(retrievalFunction, inputValidation, new DefaultExceptionHandler());
    }

    /**
     * See {@link #execute(Supplier, InputValidatorChain, OutputValidatorChain, ExceptionHandler)}
     *
     * @param retrievalFunction The {@code Supplier} for fetching the data from the data layer.
     * @param exceptionHandler  A {@code ExceptionHandler} that will be used to handle any exception that might be raised by the data layer.
     * @param <T>               The type of the data that should be retrieved and validated.
     * @return The result of the {@code retrievalFunction}.
     */
    public static <T> Either<ServerError, T> execute(Supplier<T> retrievalFunction, ExceptionHandler exceptionHandler) {
        return execute(retrievalFunction, new InputValidatorChain(), exceptionHandler);
    }

    /**
     * See {@link #execute(Supplier, InputValidatorChain, OutputValidatorChain, ExceptionHandler)}
     *
     * @param retrievalFunction The {@code Supplier} for fetching the data from the data layer.
     * @param <T>               The type of the data that should be retrieved and validated.
     * @return The result of the {@code retrievalFunction}.
     */
    public static <T> Either<ServerError, T> execute(Supplier<T> retrievalFunction) {
        return execute(retrievalFunction, new DefaultExceptionHandler());
    }
}

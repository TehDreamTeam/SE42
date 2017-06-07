package nl.tehdreamteam.se42.logic;

import com.jnape.palatable.lambda.adt.Either;
import nl.tehdreamteam.se42.logic.exception.ExceptionHandler;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.validator.input.InputValidatorChain;
import nl.tehdreamteam.se42.logic.validator.output.OutputValidatorChain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Oscar de Leeuw
 */
public class BusinessLogicTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private ServerError serverError = new ServerError("Test", 500);
    private Optional<ServerError> optionalServerError = Optional.of(serverError);
    private InputValidatorChain inputValidation;
    private OutputValidatorChain outputValidation;
    private Supplier retrievalFunction;
    private ExceptionHandler exceptionHandler;

    @Before
    public void setUp() throws Exception {
        inputValidation = Mockito.mock(InputValidatorChain.class);
        outputValidation = Mockito.mock(OutputValidatorChain.class);
        retrievalFunction = Mockito.mock(Supplier.class);
        exceptionHandler = Mockito.mock(ExceptionHandler.class);
    }

    @Test
    public void execute_withInvalidInput_returnsServerError() throws Exception {
        inputValidationWillFail();
        verifyErrorHappened();
    }

    @Test
    public void execute_withDatabaseError_throwsException() throws Exception {
        retrievalFunctionWillFail();
        verifyErrorHappened();
    }

    @Test
    public void execute_withInvalidOutput_throwsException() throws Exception {
        retrievalFunctionWillReturnObject();
        outputValidationWillFail();
        verifyErrorHappened();
    }

    @Test
    public void execute_withNullDatabaseReturn_returnsNull() throws Exception {
        expectedException.expect(IllegalStateException.class);
        retrievalFunctionWillReturnNull();
        executeBusinessLogic();
    }

    @Test
    public void execute_withValidInput_returnsObject() throws Exception {
        retrievalFunctionWillReturnObject();

        Object actual = executeBusinessLogic();
        Assert.assertNotNull(actual);
        verifyModels();
    }

    private void verifyErrorHappened() {
        Either<ServerError, Object> actual = executeBusinessLogic();
        ServerError expected = serverError;

        Assert.assertSame(expected, actual.projectA().get());
    }

    private Either<ServerError, Object> executeBusinessLogic() {
        return BusinessLogic.execute(retrievalFunction, inputValidation, outputValidation, exceptionHandler);
    }

    private void verifyModels() {
        Mockito.verify(inputValidation).validate();
        Mockito.verify(retrievalFunction).get();
        Mockito.verify(outputValidation).validate(ArgumentMatchers.any());
    }

    private void inputValidationWillFail() {
        Mockito.doReturn(optionalServerError).when(inputValidation).validate();
    }

    private void retrievalFunctionWillFail() {
        RuntimeException exception = new RuntimeException();

        Mockito.when(retrievalFunction.get()).thenThrow(exception);
        Mockito.when(exceptionHandler.handle(exception)).thenReturn(serverError);
    }

    private void retrievalFunctionWillReturnNull() {
        Mockito.when(retrievalFunction.get()).thenReturn(null);
    }

    private void retrievalFunctionWillReturnObject() {
        Mockito.when(retrievalFunction.get()).thenReturn(new Object());
    }

    private void outputValidationWillFail() {
        Mockito.doReturn(optionalServerError).when(outputValidation).validate(ArgumentMatchers.any());
    }
}
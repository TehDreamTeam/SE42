package nl.tehdreamteam.se42.logic.user;

import com.jnape.palatable.lambda.adt.Either;
import nl.tehdreamteam.se42.data.user.UserRepository;
import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.logic.exception.ErrorType;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * @author Oscar de Leeuw
 */
public class DefaultUserControllerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private UserRepository repository;
    private DefaultUserController userController;
    private User user;
    private Either<ServerError, User> loginResponse;
    private String username = "Henk";
    private String password = "correct";

    @Before
    public void setUp() throws Exception {
        repository = Mockito.mock(UserRepository.class);
        userController = new DefaultUserController(repository);
        user = new User(new LoginCredentials(username, password));
    }

    @Test
    public void login_withValidInput_callsRepository() throws Exception {
        repositoryReturnsUser();
        getLoginResponseWithValidCredentials();
        checkLoginResponseReturnsUser();
        verifyRepositoryCalled();
    }

    @Test
    public void login_withWrongPassword_returnsError() throws Exception {
        repositoryReturnsUser();
        getLoginResponseWithWrongPassword();
        checkLoginResponseReturnsPasswordError();
        verifyRepositoryCalled();
    }

    @Test
    public void login_withNullPassword_returnsError() throws Exception {
        repositoryReturnsUser();
        getLoginResponseWithNullPassword();
        checkLoginResponseReturnsEmptyError();
        verifyRepositoryNotCalled();
    }

    private void repositoryReturnsUser() {
        Mockito.when(repository.get(username)).thenReturn(user);
    }

    private void getLoginResponseWithValidCredentials() {
        loginResponse = userController.login(username, password);
    }

    private void getLoginResponseWithWrongPassword() {
        loginResponse = userController.login(username, "false");
    }

    private void getLoginResponseWithNullPassword() {
        loginResponse = userController.login(username, null);
    }

    private void checkLoginResponseReturnsUser() {
        User actual = loginResponse.orThrow(x -> new IllegalArgumentException("NO"));
        User expected = user;

        assertSame(expected, actual);
    }

    private void checkLoginResponseReturnsPasswordError() {
        ServerError actual = loginResponse.forfeit(r -> null);

        assertEquals(ErrorType.INVALID_PASSWORD.code(), actual.getCode());
    }

    private void checkLoginResponseReturnsEmptyError() {
        ServerError actual = loginResponse.forfeit(r -> null);

        assertEquals(ErrorType.EMPTY_VALUE.code(), actual.getCode());
    }

    private void verifyRepositoryCalled() {
        Mockito.verify(repository).get(ArgumentMatchers.anyString());
    }

    private void verifyRepositoryNotCalled() {
        Mockito.verify(repository, Mockito.never()).get(ArgumentMatchers.anyString());
    }

}
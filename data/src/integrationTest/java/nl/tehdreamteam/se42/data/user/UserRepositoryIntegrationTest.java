package nl.tehdreamteam.se42.data.user;

import nl.tehdreamteam.se42.data.user.hibernate.UserHibernateRepository;
import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public final class UserRepositoryIntegrationTest {

    private UserRepository repository;
    private User user;

    @Test
    public void createAndGet_whenCalled_createsUserAndPersists() {
        givenHibernateUserRepository();
        givenDefaultUser();

        saveUser();

        verifyUserIsAdded();
    }

    @Before
    public void resetFields() {
        repository = null;
        user = null;
    }

    @After
    public void resetPersistency() {
        deleteUser();
    }

    private void saveUser() {
        repository.save(user);
    }

    private void deleteUser() {
        repository.delete(user);
    }

    private void verifyUserIsAdded() {
        User expected = user;
        User actual = repository.get(expected.getLoginCredentials().getUsername());

        String expectedUsername = user.getLoginCredentials().getUsername();
        String actualUsername = user.getLoginCredentials().getUsername();
        assertEquals(expectedUsername, actualUsername);
    }

    private void givenDefaultUser() {
        LoginCredentials credentials = new LoginCredentials("username", "password");
        user = new User(credentials);
    }

    private void givenHibernateUserRepository() {
        repository = new UserHibernateRepository();
    }

}

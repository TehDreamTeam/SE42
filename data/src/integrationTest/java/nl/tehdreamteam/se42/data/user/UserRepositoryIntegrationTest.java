package nl.tehdreamteam.se42.data.user;

import nl.tehdreamteam.se42.data.user.hibernate.UserHibernateRepository;
import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.NoResultException;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public final class UserRepositoryIntegrationTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private UserRepository repository;
    private User user;

    private boolean deleted;

    @Test
    public void createAndGet_whenCalled_createsUserAndPersists() {
        givenHibernateUserRepository();
        givenDefaultUser();

        saveUser();

        verifyUserIsAdded();
    }

    @Test
    public void removeUserById_whenCalled_deletesUser() {
        givenHibernateUserRepository();
        givenDefaultUser();

        saveUser();
        removeUserById();

        verifyUserIsNotAdded();
    }

    @Before
    public void resetFields() {
        repository = null;
        user = null;
        deleted = false;
    }

    @After
    public void resetPersistency() {
        if (!deleted) {
            removeUser();
        }
    }

    private void saveUser() {
        repository.save(user);
    }

    private void removeUser() {
        repository.remove(user);
        deleted = true;
    }

    private void removeUserById() {
        repository.remove(user.getId());
        deleted = true;
    }

    private void verifyUserIsNotAdded() {
        expectedException.expect(NoResultException.class);
        expectedException.expectMessage("No entity found for query");

        repository.get(user.getLoginCredentials().getUsername());
    }

    private void verifyUserIsAdded() {
        User expected = user;
        User actual = repository.get(expected.getLoginCredentials().getUsername());

        assertEquals(expected.getId(), actual.getId());
    }

    private void givenDefaultUser() {
        LoginCredentials credentials = new LoginCredentials("some_test_username", "password");
        user = new User(credentials);
    }

    private void givenHibernateUserRepository() {
        repository = new UserHibernateRepository();
    }

}

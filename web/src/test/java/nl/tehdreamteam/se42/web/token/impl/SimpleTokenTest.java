package nl.tehdreamteam.se42.web.token.impl;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.token.Token;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SimpleTokenTest {

    private Token token;
    private String id;
    private User user;

    @Before
    public void resetFields() {
        token = null;
        id = null;
        user = null;
    }

    @Test
    public void invalidate_whenCalled_invalidatesToken() {
        createSimpleToken();
        invalidateToken();

        verifyIsInvalid();
    }

    @Test
    public void isValid_byDefault_returnsTrue() {
        createSimpleToken();

        verifyIsValid();
    }

    @Test
    public void getId_whenCalled_getsId() {
        givenStandardId();
        createSimpleToken();

        verifyIdsEqual();
    }

    @Test
    public void getUser_whenCalled_getsUser() {
        givenStandardUser();
        createSimpleToken();

        verifyUsersEqual();
    }

    private void verifyIsValid() {
        verifyIsValid(true);
    }

    private void verifyIsInvalid() {
        verifyIsValid(false);
    }

    private void verifyIsValid(boolean valid) {
        assertThat(token.isValid(), is(valid));
    }

    private void verifyIdsEqual() {
        assertThat(token.getId(), is(id));
    }

    private void verifyUsersEqual() {
        createSimpleToken();

        assertThat(token.getUser(), is(user));
    }

    private void invalidateToken() {
        token.invalidate();
    }

    private void createSimpleToken() {
        token = new SimpleToken(id, user);
    }

    private void givenStandardId() {
        id = "id";
    }

    private void givenStandardUser() {
        user = mock(User.class);
    }

}
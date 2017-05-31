package nl.tehdreamteam.se42.web.token.generator;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.token.Token;
import nl.tehdreamteam.se42.web.token.generator.id.TokenIdGenerator;
import nl.tehdreamteam.se42.web.token.generator.id.impl.UUIDTokenIdGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public abstract class AbstractTokenGeneratorTest {

    private static final String ID_TO_GENERATE = "id";

    private TokenGenerator generator;
    private TokenIdGenerator idGenerator;
    private Token token;
    private User user;

    @Before
    public void resetFields() {
        generator = null;
        idGenerator = null;
        token = null;
        user = null;
    }

    @Test
    public final void construct_byDefault_usesUUIDTokenIdGenerator() {
        givenDefaultTokenGenerator();

        verifyIdGeneratorIsUUIDIdGenerator();
    }

    @Test
    public final void construct_withIdGenerator_usesGivenIdGenerator() {
        givenTokenGeneratorWithIdGenerator();

        verifyIdGeneratorIsEqual();
    }

    @Test
    public final void setIdGenerator_withIdGenerator_usesGivenIdGenerator() {
        givenDefaultTokenGenerator();

        setIdGenerator();

        verifyIdGeneratorIsEqual();
    }

    @Test
    public final void generate_whenCalled_generatesValidToken() {
        givenDefaultTokenGenerator();
        givenDefaultUser();

        generateToken();

        verifyGeneratedTokenIsValid();
    }

    @Test
    public final void generate_withUser_generatesTokenWithSameUser() {
        givenDefaultTokenGenerator();
        givenDefaultUser();

        generateToken();

        verifyGeneratedTokenUserIsEqual();
    }

    @Test
    public final void generate_whenCalled_generatesTokenWithIdFromTokenGenerator() {
        givenTokenGeneratorWithIdGenerator();
        givenDefaultUser();

        generateToken();

        verifyGeneratedIdIsEqual();
    }

    private void verifyGeneratedTokenIsValid() {
        assertTrue(token.isValid());
    }

    private void verifyGeneratedIdIsEqual() {
        assertThat(token.getId(), is(ID_TO_GENERATE));
    }

    private void verifyGeneratedTokenUserIsEqual() {
        assertThat(token.getUser(), is(user));
    }

    private void verifyIdGeneratorIsUUIDIdGenerator() {
        assertTrue(generator.getIdGenerator() instanceof UUIDTokenIdGenerator);
    }

    private void verifyIdGeneratorIsEqual() {
        assertThat(generator.getIdGenerator(), is(idGenerator));
    }

    private void generateToken() {
        token = generator.generate(user);
    }

    private void setIdGenerator() {
        idGenerator = createIdGenerator();
        generator.setIdGenerator(idGenerator);
    }

    private void givenDefaultTokenGenerator() {
        generator = constructDefaultTokenGenerator();
    }

    private void givenTokenGeneratorWithIdGenerator() {
        idGenerator = createIdGenerator();
        generator = constructTokenGenerator(idGenerator);
    }

    private void givenDefaultUser() {
        user = mock(User.class);
    }

    private TokenIdGenerator createIdGenerator() {
        TokenIdGenerator generator = mock(TokenIdGenerator.class);
        doReturn(ID_TO_GENERATE).when(generator).generate();

        return generator;
    }

    protected abstract TokenGenerator constructDefaultTokenGenerator();

    protected abstract TokenGenerator constructTokenGenerator(TokenIdGenerator idGenerator);

}
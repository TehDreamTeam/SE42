package nl.tehdreamteam.se42.web.token;

import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.web.token.generator.TokenGenerator;
import nl.tehdreamteam.se42.web.token.generator.impl.SimpleTokenGenerator;
import nl.tehdreamteam.se42.web.token.impl.SimpleToken;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public abstract class AbstractTokenRegistryTest {

    private static final Token TOKEN_TO_GENERATE = new SimpleToken("id", mock(User.class));

    private TokenRegistry registry;
    private TokenGenerator generator;
    private Token token;
    private Token userToken;
    private Token getOrRegisterToken;
    private Token oldToken;
    private User user;

    @Before
    public void resetFields() {
        registry = null;
        generator = null;
        token = null;
        userToken = null;
        getOrRegisterToken = null;
        oldToken = null;
        user = null;
    }

    @Test
    public final void register_whenCalled_generatesTokenUsingGenerator() {
        givenTokenRegistryWithGenerator();
        givenStandardUser();

        registerToken();

        verifyGeneratedTokenIsEqual();
    }

    @Test
    public final void register_whenCalled_registersTokenForUser() {
        givenTokenRegistryWithGenerator();
        givenStandardUser();

        registerToken();

        verifyGeneratedTokenIsRegistered();
    }

    @Test
    public final void register_whenCalled_registersUserForToken() {
        givenTokenRegistryWithGenerator();
        givenStandardUser();

        registerToken();

        verifyGeneratedTokenIsRegisteredForUser();
    }

    @Test
    public final void register_withAlreadyRegisteredUser_invalidatesOldToken() {
        givenTokenRegistryWithGenerator();
        givenStandardUser();

        registerToken();
        registerNewToken();

        verifyOldTokenIsInvalid();
        verifyGeneratedTokenIsRegistered();
    }

    @Test
    public final void register_withAlreadyRegisteredUser_deregistersOldToken() {
        givenDefaultTokenRegistry();
        givenStandardUser();

        registerToken();
        registerNewToken();

        verifyOldTokenIsDeregistered();
    }

    @Test
    public final void register_withAlreadyRegisteredUser_registersNewToken() {
        givenDefaultTokenRegistry();
        givenStandardUser();

        registerToken();
        registerNewToken();

        verifyGeneratedTokenIsRegistered();
    }

    @Test
    public final void deregister_withRegisteredToken_deregistersToken() {
        givenDefaultTokenRegistry();
        givenStandardUser();

        registerToken();
        deregisterToken();

        verifyGeneratedTokenIsDeregistered();
    }

    @Test
    public final void deregister_withToken_invalidatesToken() {
        givenDefaultTokenRegistry();
        givenStandardUser();

        registerToken();
        deregisterToken();

        verifyTokenIsInvalid();
    }

    @Test
    public final void getOrRegister_withUnregisteredToken_registersToken() {
        givenDefaultTokenRegistry();
        givenStandardUser();

        getOrRegisterToken();

        verifyGetOrRegisterTokenIsRegistered();
    }

    @Test
    public final void getOrRegister_withRegisteredToken_returnsSameToken() {
        givenDefaultTokenRegistry();
        givenStandardUser();

        registerToken();
        getOrRegisterToken();

        verifyGetOrRegisterTokenIsEqual();
    }

    @Test
    public final void construct_byDefault_usesSimpleTokenGenerator() {
        givenDefaultTokenRegistry();

        verifyTokenGeneratorIsSimpleTokenGenerator();
    }

    @Test
    public final void construct_generator_usesGivenGenerator() {
        givenTokenRegistryWithGenerator();

        verifyTokenGeneratorIsEqual();
    }

    @Test
    public final void setGenerator_withGenerator_usesGivenGenerator() {
        givenDefaultTokenRegistry();

        setTokenGenerator();

        verifyTokenGeneratorIsEqual();
    }

    private void verifyTokenIsInvalid() {
        assertFalse(token.isValid());
    }

    private void verifyGeneratedTokenIsDeregistered() {
        assertThat(registry.get(user), is(not(token)));
    }

    private void verifyOldTokenIsDeregistered() {
        assertThat(registry.get(user), is(not(oldToken)));
    }

    private void verifyOldTokenIsInvalid() {
        assertFalse(token.isValid());
    }

    private void verifyGetOrRegisterTokenIsEqual() {
        assertThat(getOrRegisterToken, is(token));
    }

    private void verifyGetOrRegisterTokenIsRegistered() {
        assertThat(registry.get(user), is(Optional.of(getOrRegisterToken)));
    }

    private void verifyGeneratedTokenIsRegistered() {
        assertThat(registry.get(user), is(Optional.of(token)));
    }

    private void verifyGeneratedTokenIsRegisteredForUser() {
        assertThat(registry.get(token.getId()), is(Optional.of(token)));
    }

    private void verifyGeneratedTokenIsEqual() {
        assertThat(token, is(TOKEN_TO_GENERATE));
    }

    private void verifyTokenGeneratorIsSimpleTokenGenerator() {
        assertTrue(registry.getGenerator() instanceof SimpleTokenGenerator);
    }

    private void verifyTokenGeneratorIsEqual() {
        assertThat(registry.getGenerator(), is(generator));
    }

    private void getOrRegisterToken() {
        getOrRegisterToken = registry.getOrRegister(user);
    }

    private void registerToken() {
        token = registry.register(user);
    }

    private void deregisterToken() {
        registry.deregister(token);
    }

    private void registerNewToken() {
        oldToken = token;
        registerToken();
    }

    private void setTokenGenerator() {
        generator = createGenerator();
        registry.setGenerator(generator);
    }

    private void givenDefaultTokenRegistry() {
        registry = constructDefaultTokenRegistry();
    }

    private void givenTokenRegistryWithGenerator() {
        generator = createGenerator();
        registry = constructTokenRegistry(generator);
    }

    private void givenStandardUser() {
        user = mock(User.class);
    }

    private TokenGenerator createGenerator() {
        TokenGenerator generator = mock(TokenGenerator.class);
        doReturn(TOKEN_TO_GENERATE).when(generator).generate(any());

        return generator;
    }

    protected abstract TokenRegistry constructDefaultTokenRegistry();

    protected abstract TokenRegistry constructTokenRegistry(TokenGenerator generator);

}
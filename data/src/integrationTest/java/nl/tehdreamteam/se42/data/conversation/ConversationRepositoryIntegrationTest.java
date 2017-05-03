package nl.tehdreamteam.se42.data.conversation;

import nl.tehdreamteam.se42.data.conversation.hibernate.ConversationHibernateRepository;
import nl.tehdreamteam.se42.data.user.UserRepository;
import nl.tehdreamteam.se42.data.user.hibernate.UserHibernateRepository;
import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.List;

/**
 * @author Oscar de Leeuw
 */
public class ConversationRepositoryIntegrationTest {

    private ConversationRepository repository;
    private UserRepository userRepository;
    private Conversation conversation;
    private User user;

    @Before
    public void setUp() throws Exception {
        repository = getRepository();
        userRepository = getUserRepository();
        user = getUser();
        conversation = getConversation();
    }

    @After
    public void tearDown() throws Exception {
        removeConversation();
        removeUser();
    }

    @Test
    public void createAndGet_whenCalled_createsConversationAndPersists() throws Exception {
        saveConversation();
        verifyConversationIsAdded();
    }

    @Test
    public void updateAndGet_whenCalled_updatesConversationAndPersists() throws Exception {
        saveConversation();
        addMessage();
        verifyMessageIsAdded();
    }

    private void addMessage() {
        conversation.addMessage(new Message("Henk is een rare jongen", user));
        repository.save(conversation);
    }

    private void saveConversation() {
        repository.save(conversation);
    }

    private void verifyConversationIsAdded() {
        Conversation conv = repository.get(conversation.getId());

        Assert.assertTrue(new ReflectionEquals(conv).matches(conversation));
    }

    private void verifyMessageIsAdded() {
        List<Message> messages = repository.get(conversation.getId()).getMessages();
        Message message = messages.get(messages.size() - 1);

        Assert.assertThat(message.getContent(), CoreMatchers.is("Henk is een rare jongen"));
    }

    private void removeConversation() {
        repository.remove(conversation.getId());
    }

    private void removeUser() {
        userRepository.remove(user.getId());
    }

    private ConversationRepository getRepository() {
        return new ConversationHibernateRepository();
    }

    private UserRepository getUserRepository() {
        return new UserHibernateRepository();
    }

    private Conversation getConversation() {
        Conversation conv = new Conversation();
        conv.addUser(user);

        conv.addMessage(new Message("Test1", user));
        conv.addMessage(new Message("Test2", user));

        return conv;
    }

    private User getUser() {
        User user = new User(new LoginCredentials("username", "password"));
        userRepository.save(user);

        return user;
    }
}

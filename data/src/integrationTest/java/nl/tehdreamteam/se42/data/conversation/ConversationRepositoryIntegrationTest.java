package nl.tehdreamteam.se42.data.conversation;

import nl.tehdreamteam.se42.data.conversation.hibernate.ConversationHibernateRepository;
import nl.tehdreamteam.se42.data.user.UserRepository;
import nl.tehdreamteam.se42.data.user.hibernate.UserHibernateRepository;
import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

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

    private void saveConversation() {
        repository.save(conversation);
    }

    private void verifyConversationIsAdded() {
        Conversation conv = repository.find(conversation.getId());

        Assert.assertTrue(new ReflectionEquals(conv).matches(conversation));

       /* Assert.assertEquals(2, conv.getMessages().size());
        Assert.assertEquals(1, conv.getParticipants().size());
        Assert.assertTrue(conv.getMessages().stream().anyMatch(s -> s.getContent().equals("Test1")));
        Assert.assertTrue(conv.getMessages().stream().anyMatch(s -> s.getContent().equals("Test2")));
        Assert.assertTrue(conv.getParticipants().stream().anyMatch(u -> u.getLoginCredentials().getUsername().equals("username")));*/
    }

    private void removeConversation() {
        repository.remove(conversation);
    }

    private void removeUser() {
        userRepository.remove(user);
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

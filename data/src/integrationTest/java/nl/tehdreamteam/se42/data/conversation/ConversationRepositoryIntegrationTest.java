package nl.tehdreamteam.se42.data.conversation;

import nl.tehdreamteam.se42.data.conversation.hibernate.ConversationHibernateRepository;
import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Oscar de Leeuw
 */
public class ConversationRepositoryIntegrationTest {

    private ConversationRepository repository;
    private Conversation conversation;

    @Before
    public void setUp() throws Exception {
        repository = getRepository();
        conversation = getConversation();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createAndGet_whenCalled_createsConversationAndPersists() throws Exception {
        saveConversation();
        verifyConversationIsAdded();
        removeConversation();
    }

    private void saveConversation() {
        repository.save(conversation);
    }

    private void verifyConversationIsAdded() {
        Conversation conv = repository.getConversationsForUser(conversation.getParticipants().get(0)).get(0); //TODO UNHACK

        Assert.assertEquals(2, conv.getMessages().size());
        Assert.assertEquals(1, conv.getParticipants().size());
        Assert.assertTrue(conv.getMessages().stream().anyMatch(s -> s.getContent().equals("Test1")));
        Assert.assertTrue(conv.getMessages().stream().anyMatch(s -> s.getContent().equals("Test2")));
        Assert.assertTrue(conv.getParticipants().stream().anyMatch(u -> u.getLoginCredentials().getUsername().equals("username")));
    }

    private void removeConversation() {

    }

    private ConversationRepository getRepository() {
        return new ConversationHibernateRepository();
    }

    private Conversation getConversation() {
        Conversation conv = new Conversation();
        User user = new User(new LoginCredentials("username", "password"));
        conv.addUser(user);

        conv.addMessage(new Message("Test1", user));
        conv.addMessage(new Message("Test2", user));

        return conv;
    }
}

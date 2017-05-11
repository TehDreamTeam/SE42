package nl.tehdreamteam.se42.web.soap.conversation;

import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.web.endpoint.ConversationEndpoint;
import nl.tehdreamteam.se42.web.soap.SoapWebServiceConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Webservice for controlling Soap request concerning {@code Conversations}.
 *
 * @author Oscar de Leeuw
 */
@WebService(targetNamespace = SoapWebServiceConstants.DEFAULT_NAMESPACE + "conversation")
public class SoapConversationEndpoint implements ConversationEndpoint {

    private static final Logger logger = LogManager.getLogger(SoapConversationEndpoint.class.getSimpleName());

    @Override
    public void addMessage(@WebParam(name = "token") String token, @WebParam(name = "content") String content, @WebParam(name = "conversation_id") long conversationId) {
        logger.debug("Soap request for adding a message (token='{}', content='{}', conversation_id='{}').", token, content, conversationId);
    }

    @Override
    public void addUserToConversation(@WebParam(name = "token") String token, @WebParam(name = "user_id") long userId, @WebParam(name = "conversation_id") long conversationId) {
        logger.debug("Soap request for adding an user to a conversation (token='{}', user_id='{}', conversation_id='{}').", token, userId, conversationId);
    }

    @Override
    public List<Message> getMessages(@WebParam(name = "token") String token, @WebParam(name = "conversation_id") long conversationId) {
        logger.debug("Soap request for getting the messages of a conversation (token='{}', conversation_id='{}').", token, conversationId);

        return null;
    }
}

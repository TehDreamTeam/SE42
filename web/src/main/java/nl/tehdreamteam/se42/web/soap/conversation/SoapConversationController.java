package nl.tehdreamteam.se42.web.soap.conversation;

import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.web.controller.ConversationController;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * @author Oscar de Leeuw
 */
@WebService
public class SoapConversationController implements ConversationController {
    @Override
    public void addMessage(@WebParam(name = "token") String token, @WebParam(name = "content") String content, @WebParam(name = "conversation_id") long conversationId) {

    }

    @Override
    public void addUserToConversation(@WebParam(name = "token") String token, @WebParam(name = "user_id") long userId, @WebParam(name = "conversation_id") long conversationId) {

    }

    @Override
    public List<Message> getMessages(@WebParam(name = "token") String token, @WebParam(name = "conversation_id") long conversationId) {
        return null;
    }
}

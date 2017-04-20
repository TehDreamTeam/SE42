package nl.tehdreamteam.se42.data;

import nl.tehdreamteam.se42.data.context.impl.MessageHibernateContext;
import nl.tehdreamteam.se42.domain.LoginCredentials;
import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;

/**
 * @author Oscar de Leeuw
 */
public class Main {
    public static void main(String... args) {
        MessageHibernateContext context = new MessageHibernateContext();
        User user = new User(new LoginCredentials("kip", "kop"));

        context.create(new Message("Henk", user));

        Message message = context.findAll().get(0);
        System.out.println(message.getContent());
    }
}

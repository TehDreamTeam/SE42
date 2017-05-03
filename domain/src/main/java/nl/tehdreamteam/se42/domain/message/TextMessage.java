package nl.tehdreamteam.se42.domain.message;

import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Oscar de Leeuw
 */
@Entity
public class TextMessage extends Message<String> {

    @Column(name = "text")
    private String textContent;

    protected TextMessage() {
    }

    public TextMessage(String content, User sender) {
        super(sender);
        this.textContent = content;
    }

    @Override
    public String getContent() {
        return textContent;
    }

    public void setContent(String content) {
        this.textContent = content;
    }

    @Override
    public String toString() {
        return String.format("%tT %s: %s", getTime(), getSender().getLoginCredentials().getUsername(), textContent);
    }
}

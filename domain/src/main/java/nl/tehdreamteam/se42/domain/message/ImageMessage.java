package nl.tehdreamteam.se42.domain.message;

import nl.tehdreamteam.se42.domain.Message;
import nl.tehdreamteam.se42.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Arrays;

/**
 * @author Oscar de Leeuw
 */
@Entity
public class ImageMessage extends Message<Byte[]> {

    @Column(name = "image")
    private Byte[] imageContent;

    protected ImageMessage() {

    }

    public ImageMessage(User sender, Byte[] content) {
        super(sender);
        this.imageContent = content;
    }

    public Byte[] getContent() {
        return imageContent;
    }

    public void setContent(Byte[] content) {
        this.imageContent = content;
    }

    @Override
    public String toString() {
        return String.format("%tT %s: %s", getTime(), getSender().getLoginCredentials().getUsername(), Arrays.toString(imageContent));
    }
}

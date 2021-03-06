package nl.tehdreamteam.se42.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * LoginCredentials are the credentials of an User.
 *
 * @author Oscar de Leeuw
 */
@Embeddable
public class LoginCredentials {

    @Column(unique = true)
    private String username;
    private String password;

    protected LoginCredentials() {

    }

    /**
     * Creates a new LoginCredentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

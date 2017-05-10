package nl.tehdreamteam.se42.web.token;

import nl.tehdreamteam.se42.domain.User;

import java.security.MessageDigest;

/**
 * Generates a SHA-256 hash based on an {@code User's} username and password.
 *
 * @author Oscar de Leeuw
 */
public class HashTokenStrategy implements TokenGenerationStrategy {

    @Override
    public String generateToken(User user) {
        return generateSHA256Hash(user.getLoginCredentials().getUsername() + user.getLoginCredentials().getPassword());
    }

    private String generateSHA256Hash(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}

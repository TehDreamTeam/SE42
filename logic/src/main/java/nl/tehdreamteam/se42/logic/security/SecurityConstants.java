package nl.tehdreamteam.se42.logic.security;

import nl.tehdreamteam.se42.logic.security.EncryptionAlgorithm.Symmetric;
import nl.tehdreamteam.se42.logic.security.symmetric.SymmetricEncryption;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Contains standard keys and ivs used in this Application.
 */
public final class SecurityConstants {

    /**
     * A {@link Byte} array, of length {@code 16}, containing the data that forms the {@link IvParameterSpec}.
     */
    public static byte[] DEFAULT_IV_SPEC = {
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 16
    };

    /**
     * A {@link Byte} array, of length {@code 16}, containing the data that forms the {@link SecretKey}.
     */
    public static byte[] DEFAULT_KEY_SPEC = {
            16, 15, 14, 13,
            12, 11, 10, 9,
            8, 7, 6, 5,
            4, 3, 2, 1
    };

    /**
     * Defines the {@code algorithm} for the {@link SymmetricEncryption}.
     */
    public static Symmetric SYMMETRIC_ENCRYPTION_ALGORITHM = Symmetric.AES;

    private SecurityConstants() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

}

package nl.tehdreamteam.se42.logic.security;

/**
 * The {@code Encryption} handles encryption/decryption of data.
 */
public interface Encryption<T extends EncryptionAlgorithm> {

    /**
     * Encrypts the given {@code data}.
     *
     * @param data The {@code data} to encrypt.
     * @return An encrypted version of the given {@code data}.
     */
    byte[] encrypt(byte[] data);

    /**
     * Decrypts the given {@code data}.
     *
     * @param data The {@code data} to decrypt.
     * @return The decrypted version of the given {@code data}.
     */
    byte[] decrypt(byte[] data);

    /**
     * Gets the {@link EncryptionAlgorithm} used by this {@code Encryption}.
     *
     * @return The {@code algorithm} used by this {@code Encryption}.
     */
    T getEncryptionAlgorithm();

}

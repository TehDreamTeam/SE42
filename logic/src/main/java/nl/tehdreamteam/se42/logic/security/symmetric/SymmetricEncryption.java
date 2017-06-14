package nl.tehdreamteam.se42.logic.security.symmetric;

import nl.tehdreamteam.se42.logic.security.Encryption;
import nl.tehdreamteam.se42.logic.security.EncryptionAlgorithm.Symmetric;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Implementation of {@link Encryption} that uses a {@link Symmetric} algorithm.
 */
public class SymmetricEncryption implements Encryption<Symmetric> {

    private final Symmetric algorithm;

    private final IvParameterSpec iv;
    private final SecretKey key;
    private final Cipher cipher;

    /**
     * Initializes this {@code SymmetricEncryption} using the given arguments.
     *
     * @param algorithm The {@code algorithm} that this {@code SymmetricEncryption} should use.
     * @param iv        The {@code iv} of the encryption.
     * @param key       The {@code key} of the encryption.
     */
    public SymmetricEncryption(Symmetric algorithm, byte[] iv, byte[] key) {
        this.algorithm = algorithm;

        try {
            cipher = Cipher.getInstance(algorithm.getAlgorithm());
            this.iv = new IvParameterSpec(iv);
            this.key = new SecretKeySpec(key, algorithm.getAlgorithmName());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] encrypt(byte[] data) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return cipher.doFinal(data);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] decrypt(byte[] data) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            return cipher.doFinal(data);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Symmetric getEncryptionAlgorithm() {
        return algorithm;
    }

}

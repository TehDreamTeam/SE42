package nl.tehdreamteam.se42.logic.security;

import nl.tehdreamteam.se42.logic.security.symmetric.SymmetricEncryption;

import javax.crypto.Cipher;

/**
 * Contains all algorithms used for a {@link Encryption} instance.
 */
public interface EncryptionAlgorithm {

    /**
     * Gets the actual name of the {@code algorithm} used by a {@link Cipher}.
     *
     * @return A {@link String} representing the actual {@code algorithm}.
     */
    String getAlgorithm();

    /**
     * Gets the {@code name} of the {@code algorithm}.
     *
     * @return A {@link String} representing the {@code name} of the {@code algorithm}.
     */
    String getAlgorithmName();

    /**
     * Defines all symmetric algorithms for a {@link SymmetricEncryption} instance.
     */
    enum Symmetric implements EncryptionAlgorithm {

        /**
         * Defines the {@code AES} algorithm.
         */
        AES("AES/CBC/PKCS5PADDING", "AES");

        private final String algorithm;
        private final String name;

        Symmetric(String algorithm, String name) {
            this.algorithm = algorithm;
            this.name = name;
        }

        @Override
        public String getAlgorithm() {
            return algorithm;
        }

        @Override
        public String getAlgorithmName() {
            return name;
        }

    }

}

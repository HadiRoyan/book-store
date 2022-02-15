package book.store.security;

import com.google.common.io.BaseEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;

public class PasswordController {
    
    private static final Logger log = LoggerFactory.getLogger(PasswordController.class);
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATIONS = 10000;
    
    private static final int SALT_SIZE = 32; // salt size at least 32 byte
    private static final int HASH_SIZE = 512;

    private PasswordController() {
    }

    public static String hash(char [] password) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] salt = generateSalt();

            log.info("Hashing password {} with hash algorithm {}, hash size {}, # of iterations {} and salt {}",
                    String.valueOf(password), ALGORITHM, HASH_SIZE, ITERATIONS, BaseEncoding.base16().encode(salt));

            byte[] hash = calculateHash(skf, password, salt);
            
            return BaseEncoding.base16().encode(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public static boolean verify(String hashPassword, char[] password) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] salt = generateSalt();
            
            byte[] hash = calculateHash(skf, password, salt);
            byte[] originalHash = BaseEncoding.base16().decode(hashPassword);
                    
            boolean correct = verifyPassword(skf, originalHash, password, salt);
            return correct;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            log.error(ex.getMessage(), ex);
            return false;
        } 
    }
    
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);

        return salt;
    }

    private static byte[] calculateHash(SecretKeyFactory skf, char[] password, byte[] salt) throws InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, HASH_SIZE);

        return skf.generateSecret(spec).getEncoded();
    }

    private static boolean verifyPassword(SecretKeyFactory skf, byte[] originalHash, char[] password, byte[] salt) throws
            InvalidKeySpecException {
        byte[] comparisonHash = calculateHash(skf, password, salt);

        log.info("hash 1: {}", BaseEncoding.base16().encode(originalHash));
        log.info("hash 2: {}", BaseEncoding.base16().encode(comparisonHash));

        return comparePasswords(originalHash, comparisonHash);
    }

    /**
     * Compares the two byte arrays in length-constant time using XOR.
     *
     * @param originalHash   The original password hash
     * @param comparisonHash The comparison password hash
     * @return True if both match, false otherwise
     */
    private static boolean comparePasswords(byte[] originalHash, byte[] comparisonHash) {
        int diff = originalHash.length ^ comparisonHash.length;
        for (int i = 0; i < originalHash.length && i < comparisonHash.length; i++) {
            diff |= originalHash[i] ^ comparisonHash[i];
        }

        return diff == 0;
    }
}

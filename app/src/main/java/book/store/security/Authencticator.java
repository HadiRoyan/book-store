package book.store.security;

import book.store.entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Authencticator {
    
    private static final Logger log = LoggerFactory.getLogger(Authencticator.class);
    
    
    public static boolean authenticates(String inputPassword, User user) {
        String calculatedHash = getEncryptedPassword(inputPassword, user.getSalt());
        
//        log.debug("salt : {}", user.getSalt());
//        log.debug("calculatedHash: {}", calculatedHash);        
//        log.debug("hashPassword: {}", user.getHashPassword());
        
        return calculatedHash.equals(user.getHashPassword());
    }
    
    public static String[] encryptPassword(char[] password) {
        log.info("hashing user password");
        
        String salt = getNewSalt();
        String encryptedPassword = getEncryptedPassword(Arrays.toString(password), salt);
        log.info("Success hash user password");
        
        String result[] = {salt, encryptedPassword};
        return result;
    }
    
    // encrypt the password
    private static String getEncryptedPassword(String password, String salt) {
        String ALGORITHM = "PBKDF2WithHmacSHA1";
        int KEY_LENGTH = 160; // for SHA-1
        int ITERATIONS = 10000;
        
        try {
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);

            byte[] encByte = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(encByte);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }
    
    // Returns base64 encoded salt
    public static String getNewSalt() {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[8];
            random.nextBytes(salt);
            return Base64.getEncoder().encodeToString(salt);
        } catch (NoSuchAlgorithmException ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }
}

package book.store.main;

import book.store.security.PasswordController;
import org.junit.jupiter.api.Test;

public class PasswordControllerTest {
    
    String password = "password123";

    @Test
    void testHash() throws Exception {
        String passwordHash = PasswordController.hash(password.toCharArray());
        System.out.println(passwordHash);
    }

    @Test
    void testVerify() {
    }
}

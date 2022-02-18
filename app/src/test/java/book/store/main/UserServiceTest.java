package book.store.main;

import book.store.entity.User;
import book.store.repository.UserRepository;
import book.store.repository.impl.UserRepositoryImpl;
import book.store.services.UserService;
import java.util.Arrays;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
 
    private UserRepository userRepository = new UserRepositoryImpl();
    private UserService userService = new UserService(userRepository);
    char[] password = "123456789".toCharArray();
    char[] passwordX = "salahuser".toCharArray();
    User user = new User("tester", "tester@test.com", "new tester", password);
    
    @Test
    void testSignUp() {
        user.setId(UUID.randomUUID().toString());
        userService.signup(user);
    }
    
    @Test
    void testLogin() {
        userService.login("new tester", Arrays.toString(password));
    }
    
    @Test
    void testLoginFailed() {
        userService.login("testerx", Arrays.toString(password));
    }
    
    @Test
    void testLoginWrongPassword() {
        userService.login("new tester", Arrays.toString(passwordX));
    }
}

package book.store.main;

import book.store.entity.User;
import book.store.repository.UserRepository;
import book.store.repository.impl.UserRepositoryImpl;
import book.store.services.UserService;
import java.util.Arrays;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class UserServiceTest {
 
    private UserRepository userRepository = new UserRepositoryImpl();
    private UserService userService = new UserService(userRepository);
    char[] password = "123456789".toCharArray();
    char[] passwordX = "salahuser".toCharArray();
    User user = new User("tester", "tester@test.com", "tester", password);
    
    @Test
    void testSignUp() {
        user.setId(UUID.randomUUID().toString());
        userService.signup(user);
    }
    
    @Test
    void testLogin() {
        var user = userService.login("tester", Arrays.toString(password));
        System.out.println(user.toString());
    }
    
    @Test
    void testLoginFailed() {
         var user = userService.login("testerx", Arrays.toString(password));
         System.out.println(user.toString());
    }
    
    @Test
    void testLoginWrongPassword() {
         var user = userService.login("tester", Arrays.toString(passwordX));
         System.out.println(user.toString());
    }
    
    
    @Test
    void testDeleteSuccess() {
        var response = userService.delete("new tester");
        Assertions.assertEquals(response.SUCCESS, response);
    }
}

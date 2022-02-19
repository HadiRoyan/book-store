package book.store.main;

import book.store.entity.User;
import book.store.repository.UserRepository;
import book.store.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {

    private UserRepository repository = new UserRepositoryImpl();
    char[] password = {'u','s','e','r'};
    private User user = new User("dummy user", password);

    @Test
    void testCreateUser() {
        repository.createUser(user);
    }
    
    @Test
    void testLogin() {
        User user = repository.login("tester");
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());
        System.out.println(user.getHashPassword());
        System.out.println(user.getSalt());
    }
    
    @Test
    void testDeleteSuccess() {
        var response = repository.deleteUser("tester");
        Assertions.assertEquals(response.SUCCESS, response);
    }
}

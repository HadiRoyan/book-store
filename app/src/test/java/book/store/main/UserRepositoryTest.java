package book.store.main;

import book.store.entity.User;
import book.store.repository.UserRepository;
import book.store.repository.impl.UserRepositoryImpl;
import book.store.security.PasswordController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class UserRepositoryTest {

    private UserRepository repository = new UserRepositoryImpl();
    char[] password = {'u','s','e','r'};
    private User user = new User("xxxyyy@gmail.com", "dummy user", password);

    @Test
    void testCreateUser() {
        repository.createUser(user);
    }
}

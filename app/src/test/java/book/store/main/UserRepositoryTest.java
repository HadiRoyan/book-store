package book.store.main;

import book.store.entity.User;
import book.store.repository.UserRepository;
import book.store.repository.impl.UserRepositoryImpl;
import book.store.util.DatabaseUtil;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {

    private UserRepository repository = new UserRepositoryImpl(DatabaseUtil.getDataSource());
    private User user = new User("xxxyyy@gmail.com", "dummy user", "xdxdxdxdxd");

    @Test
    void testCreateUser() {
        repository.createUser(user);
    }
}

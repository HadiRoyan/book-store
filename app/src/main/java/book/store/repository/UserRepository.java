package book.store.repository;

import book.store.entity.Response;
import book.store.entity.User;

public interface UserRepository {

    void login(String username, String password);

    Response createUser(User user);
}

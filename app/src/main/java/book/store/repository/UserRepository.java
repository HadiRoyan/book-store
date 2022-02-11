package book.store.repository;

import book.store.entity.User;

public interface UserRepository {

    void login(String username, String password);

    void createUser(User user);
}

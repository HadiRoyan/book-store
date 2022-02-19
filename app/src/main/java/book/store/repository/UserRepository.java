package book.store.repository;

import book.store.entity.Response;
import book.store.entity.User;

public interface UserRepository {

    User login(String username);

    Response createUser(User user);
    
    Response deleteUser(String username);
    
}

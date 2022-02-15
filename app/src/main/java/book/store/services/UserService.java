package book.store.services;

import book.store.entity.Response;
import book.store.entity.User;
import book.store.repository.UserRepository;
import book.store.repository.impl.UserRepositoryImpl;
import book.store.security.PasswordController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    UserRepository repository = new UserRepositoryImpl();

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }
    
    public Response signup(User user) {
        
        String hash = PasswordController.hash(user.getPassword());
        if (hash == null) {
            log.error("Response : "+ Response.SUCCESS.getDescription() + " An exception occurred!", new NullPointerException());
            return Response.ERROR; // bad request beacuse password is null
        }
        
        user.setHashPassword(hash);
        var response = repository.createUser(user);
        
        log.info("RESPONSE: "+ response);
        return response;
    }
}

package book.store.services;

import book.store.entity.Response;
import book.store.entity.User;
import book.store.repository.UserRepository;
import book.store.repository.impl.UserRepositoryImpl;
import book.store.security.Authencticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    UserRepository repository = new UserRepositoryImpl();

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }
    
    public Response signup(User user) {
        
        String[] encryptionPass = Authencticator.encryptPassword(user.getPassword());
        user.setSalt(encryptionPass[0]);
        user.setHashPassword(encryptionPass[1]);
        log.info("Saving [user={}, password={}]",user.getUsername(), user.getHashPassword());
        
        var response = repository.createUser(user);
        log.info("Response: "+ response);
        
        return response;
    }
    
    public User login(String username, String password) {
        User user = repository.login(username);
        
        if (user != null) {
            log.info("user is found trying to authenticate for username : {} ", user.getUsername());
            boolean valid = Authencticator.authenticates(password, user);
            log.info("authentication for username : {} = {}", user.getUsername(), valid);

            if (valid) {
                return user;
            } else {
                return new User();
            }
        } else {
            log.error("Failed to authentice user because {} is not found", username);
            return null;
        }
    }
    
    public Response delete(String username) {
        return repository.deleteUser(username);
    }
}

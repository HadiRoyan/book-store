package book.store.repository.impl;

import book.store.entity.Response;
import book.store.entity.User;
import book.store.repository.UserRepository;
import book.store.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepositoryImpl implements UserRepository {

    private final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public UserRepositoryImpl() {
        
    }

    @Override
    public User login(String username) {
        log.info("Request : login");
        if (isUserExist(username)) {
            
            String sql = """
                     SELECT id, name, email, username, password, salt FROM user WHERE username= ? 
                     """;
            try (Connection connection = new DatabaseUtil().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, username);
                User user = new User();
                try (ResultSet resultSet = statement.executeQuery()) {

                    while(resultSet.next()) {
                        user.setId(resultSet.getString("id"));
                        user.setEmail(resultSet.getString("email"));
                        user.setName(resultSet.getString("name"));
                        user.setUsername(resultSet.getString("username"));
                        user.setHashPassword(resultSet.getString("password"));
                        user.setSalt(resultSet.getString("salt"));
                    }
                }
                log.info("Response : ok - {}", user.toString());
                return user;
            } catch (SQLException ex) {
                log.error("Response : {}", Response.ERROR.name());
                throw new RuntimeException(ex);
            }
            
        } else {
            log.info("Response : user not found");
            return null;
        }
        
        
    }

    @Override
    public Response createUser(User user) {
        log.info("Request : Sign Up");
        String sql = """
                INSERT INTO user(id, name, email, username, password, salt) VALUES(?, ?, ?, ?, ?, ?) 
                """;

        try (Connection connection = new DatabaseUtil().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getHashPassword());
            statement.setString(6, user.getSalt());

            statement.executeUpdate();
            log.info("Response : {} - user created", Response.SUCCESS.getDescription());
            return Response.SUCCESS; // ok
        } catch (SQLException e) {
            log.error("Response : {}", Response.ERROR.name());
            log.error(e.getMessage(), e);
            return Response.ERROR; // error
        }
    }
    
    @Override
    public Response deleteUser(String username) {
        log.info("Request : delete");
        String sql = """
                DELETE FROM user WHERE username=?
                """;
        
        if (isUserExist(username)) {
            try (Connection connection = new DatabaseUtil().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
            ){
                statement.setString(1, username);
                statement.executeUpdate();
                log.info("Response : {} - user has been deleted", Response.SUCCESS.getDescription());
                return Response.SUCCESS;
            }catch (SQLException ex) {
                log.error("Response : {}", Response.ERROR.name());
                log.error(ex.getMessage(), ex);
                return Response.ERROR;
            }
        } else {
            log.info("Response : user not found");
            return Response.FAILED;
        }
        
    }
    
    private boolean isUserExist(String username) {
        String sql = """
                SELECT username FROM user WHERE username= ? 
                """;

        try (Connection connection = new DatabaseUtil().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
 
    }

}       
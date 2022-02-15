package book.store.repository.impl;

import book.store.entity.Response;
import book.store.entity.User;
import book.store.repository.UserRepository;
import book.store.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepositoryImpl implements UserRepository {

    private final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public UserRepositoryImpl() {
        
    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public Response createUser(User user) {
        log.info("open connection to database");
        String sql = """
                INSERT INTO user(email, username, password) VALUES(?, ?, ?)
                """;

        try (Connection connection = new DatabaseUtil().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getHashPassword());

            statement.executeUpdate();
            log.info("success create user");
            return Response.SUCCESS; // ok
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Response.ERROR; // error
        }
    }
}

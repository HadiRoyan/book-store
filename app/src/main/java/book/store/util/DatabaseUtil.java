package book.store.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Slf4j
public class DatabaseUtil {

    private final Logger log = LoggerFactory.getLogger(DatabaseUtil.class);
    private Connection connection;

    public Connection getConnection() {

        String url = "jdbc:sqlite:book_store.db";

        try {
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return connection;
    }

}

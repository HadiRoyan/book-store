package book.store.main;

import book.store.util.DatabaseUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

    @Test
    void testConnectionWithoutDatasource() {
        try {
            Connection connection = new DatabaseUtil().getConnection();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

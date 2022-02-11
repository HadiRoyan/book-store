package book.store.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtil {

    public static HikariDataSource dataSource;

    static {
        HikariConfig configuration = new HikariConfig();
        configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
        configuration.setUsername("root");
        configuration.setPassword("root");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/book_store");

        // pool
        configuration.setMaximumPoolSize(10);
        configuration.setMinimumIdle(5);
        configuration.setIdleTimeout(60_000);
        configuration.setMaxLifetime(60 * 60 * 1000);

        dataSource = new HikariDataSource(configuration);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}

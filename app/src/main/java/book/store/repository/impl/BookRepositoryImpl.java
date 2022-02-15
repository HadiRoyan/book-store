package book.store.repository.impl;

import book.store.entity.Book;
import book.store.entity.Response;
import book.store.repository.BookRepository;
import book.store.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Slf4j
public class BookRepositoryImpl implements BookRepository {
    
    private final Logger log = LoggerFactory.getLogger(BookRepositoryImpl.class);

    @Override
    public Response save(Book book) {
        if (!isBookExist(book.getName())) {
            String sql = """
                INSERT INTO book(name, author, publisher, quantity) VALUES(?, ?, ?, ?)
                """;
            try (
                    Connection connection = new DatabaseUtil().getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                log.info("save book to database");
                statement.setString(1, book.getName());
                statement.setString(2, book.getAuthor());
                statement.setString(3, book.getPublisher());
                statement.setInt(4, book.getQuantity());
                statement.executeUpdate();
                
                log.info("success save book " + book.getName());
                return Response.SUCCESS; // Ok
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                return Response.ERROR; // Error
            }
        } else {
            log.info(book.getName() + " is already saved");
            return Response.ERROR; // Error
        }
    }

    @Override
    public void delete(String bookName) {
        if (isBookExist(bookName)) {
            String sql = """
                    DELETE FROM book WHERE name=?
                    """;
            try (
                    Connection connection = new DatabaseUtil().getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, bookName);
                statement.executeUpdate();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public Book read(String bookName) {
        if (isBookExist(bookName)) {
            String sql = """
                    SELECT id, name, author, publisher, quantity FROM book WHERE name=?
                    """;
            try (
                    Connection connection = new DatabaseUtil().getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, bookName);
                Book book = new Book();

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        book.setId(resultSet.getInt("id"));
                        book.setName(resultSet.getString("name"));
                        book.setAuthor(resultSet.getString("author"));
                        book.setPublisher(resultSet.getString("publisher"));
                        book.setQuantity(resultSet.getInt("quantity"));
                    }
                }

                return book;
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                return null;
            }
        } else {
            return null;
        }
    }

    private boolean isBookExist(String bookName) {
        String sql = """
                SELECT name FROM book WHERE name = ?
                """;

        try (
                Connection connection = new DatabaseUtil().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, bookName);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}

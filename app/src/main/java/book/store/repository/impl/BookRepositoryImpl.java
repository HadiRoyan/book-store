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
        log.info("Request : save book");
        if (!isBookExist(book.getName())) {
            String sql = """
                INSERT INTO book(name, author, publisher, quantity) VALUES(?, ?, ?, ?)
                """;
            try (
                    Connection connection = new DatabaseUtil().getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, book.getName());
                statement.setString(2, book.getAuthor());
                statement.setString(3, book.getPublisher());
                statement.setInt(4, book.getQuantity());
                statement.executeUpdate();
                
                log.info("Response : {}", Response.SUCCESS);
                return Response.SUCCESS; // Ok
            } catch (SQLException e) {
                log.error("Response : SERVER {}", Response.ERROR);
                throw new RuntimeException(e.getMessage()); // Error
            }
        } else {
            log.info("Response : Failed save book - Duplicate name book : {}", book.getName());
            return Response.ERROR; // Cannot dupilcate book name
        }
    }

    @Override
    public Response delete(String bookName) {
        log.info("Request : delete book");
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
                log.info("Response : {}", Response.SUCCESS);
                return Response.SUCCESS;
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                return Response.ERROR;
            }
        } else {
            log.info("Response : {} - book not found", Response.FAILED);
            return Response.FAILED;
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
                log.error("Response : SERVER {}", Response.ERROR);
                throw new RuntimeException(e.getMessage()); // Error
            }
        } else {
            log.info("Response : Book is not found");
            return null;
        }
    }
    
    @Override
    public Response update(Book book) {
        log.info("Request : Update book");
        if (isBookExist(book.getName())) {
            String sql = """
                UPDATE book SET author = ?, publisher = ?, quantity = ? WHERE name = ?
                """;
            try (
                    Connection connection = new DatabaseUtil().getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, book.getAuthor());
                statement.setString(2, book.getPublisher());
                statement.setInt(3, book.getQuantity());
                statement.setString(4, book.getName());
                statement.executeUpdate();
                
                log.info("Response : {}", Response.SUCCESS);
                return Response.SUCCESS; // Ok
            } catch (SQLException e) {
                log.error("Response : SERVER {} - {}", Response.ERROR, e.getMessage());
                throw new RuntimeException(e.getMessage()); // Error
            }
        } else {
            log.info("Response : Failed update book - book is not found : {}", book.getName());
            return Response.FAILED; // Cannot dupilcate book name
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

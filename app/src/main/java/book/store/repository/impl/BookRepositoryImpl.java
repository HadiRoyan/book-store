package book.store.repository.impl;

import book.store.entity.Book;
import book.store.repository.BookRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepositoryImpl implements BookRepository {

    private DataSource dataSource;

    public BookRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Book book) {
        String sql = """
                INSERT INTO book(name, author, publisher, quantity) VALUES(?, ?, ?, ?)
                """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getQuantity());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(String bookName) {
        if (isBookExist(bookName)) {
            String sql = """
                    DELETE FROM book WHERE name=?
                    """;
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, bookName);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
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
                    Connection connection = dataSource.getConnection();
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
                throw new RuntimeException();
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
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, bookName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}

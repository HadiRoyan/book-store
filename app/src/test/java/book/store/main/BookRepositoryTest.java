package book.store.main;

import book.store.entity.Book;
import book.store.repository.BookRepository;
import book.store.repository.impl.BookRepositoryImpl;
import book.store.util.DatabaseUtil;
import org.junit.jupiter.api.Test;

public class BookRepositoryTest {

    private BookRepository repository = new BookRepositoryImpl(DatabaseUtil.getDataSource());
    private Book book = new Book("bcd", "dummy user", "CV APA AJA", 1);

    @Test
    void testSave() {
        repository.save(book);
    }

    @Test
    void testDelete() {
        repository.delete("bcd");
    }

    @Test
    void testRead() {
        Book book = repository.read("abc");
        System.out.println(book.getId());
        System.out.println(book.getName());
        System.out.println(book.getAuthor());
        System.out.println(book.getPublisher());
        System.out.println(book.getQuantity());
    }
}

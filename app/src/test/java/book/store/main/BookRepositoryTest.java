package book.store.main;

import book.store.entity.Book;
import book.store.repository.BookRepository;
import book.store.repository.impl.BookRepositoryImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class BookRepositoryTest {

    private BookRepository repository = new BookRepositoryImpl();
    private Book book = new Book("abc", "dummy user", "CV APA AJA", 1);

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
        Book book = repository.read("bcd");
        System.out.println(book.getId());
        System.out.println(book.getName());
        System.out.println(book.getAuthor());
        System.out.println(book.getPublisher());
        System.out.println(book.getQuantity());
    }
}

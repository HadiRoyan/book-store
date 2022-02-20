package book.store.main;

import book.store.repository.BookRepository;
import book.store.repository.impl.BookRepositoryImpl;
import book.store.services.BookService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class BookServiceTest {
    
    private BookRepository bookRepository  = new BookRepositoryImpl();
    private BookService bookService = new BookService(bookRepository);
    
    @Test
    void testRead() {
        var book = bookService.read("abx");
        System.out.println(book.toString());
    }
    
}

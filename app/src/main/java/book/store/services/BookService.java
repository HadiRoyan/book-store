package book.store.services;

import book.store.entity.Book;
import book.store.entity.Response;
import book.store.repository.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Slf4j
public class BookService {

    private final Logger log = LoggerFactory.getLogger(BookService.class);
    
    private BookRepository repository;

    public BookService(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    public Response save(Book book) {
        log.info("save book : {}", book.getName());
        return repository.save(book);
    }

    public Book read(String bookName) {
        log.debug("search book : {}", bookName);
        var book = repository.read(bookName);
        
        if (book != null) {
            log.info("Book found");
            return book;
        } else {
            log.info("book is not found");
            return null;
        }
    }

    public Response delete(String bookName) {
        return repository.delete(bookName);
    }
}

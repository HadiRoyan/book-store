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
        log.info("saving book : " + book.getName());
        
        var response = repository.save(book);
        log.info("Response : "+ response);
        
        return response;
    }

    public void read(String bookName) {
        repository.read(bookName);
    }

    public void delete(String bookName) {
        repository.delete(bookName);
    }
}

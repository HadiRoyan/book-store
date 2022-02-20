package book.store.repository;

import book.store.entity.Book;
import book.store.entity.Response;

public interface BookRepository {

    Response save(Book book);

    Response delete(String bookName);

    Book read(String bookName);
    
    Response update(Book book);
}

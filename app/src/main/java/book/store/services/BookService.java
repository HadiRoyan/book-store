package book.store.services;

import book.store.entity.Book;
import book.store.repository.BookRepository;

public class BookService {

    private BookRepository repository;

    public BookService(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    public void save(Book book) {
        repository.save(book);
    }

    public void read(String bookName) {
        repository.read(bookName);
    }

    public void delete(String bookName) {
        repository.delete(bookName);
    }
}

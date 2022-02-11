package book.store.repository;

import book.store.entity.Book;

public interface BookRepository {

    void save(Book book);

    void delete(String bookName);

    Book read(String bookName);
}

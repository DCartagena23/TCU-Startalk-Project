package edu.cs.tcu.tcustartalkproject.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    /**
     * Repository for basic operations: findAll(), findById(), delete(), save(), update()
     */
    private final BookRepository bookRepository;

    /**
     * Constructor for Book Service.
     * @param bookRepository Book Repository supports basic operations
     */
    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    /**
     * Method to query all books.
     * @return List of found books.
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * Method to query one book by index.
     * @param id index of the book to be sought.
     * @return Book to be sought.
     */
    public Book findById(final String id) {
        return bookRepository.findById(id).get();
    }

    /**
     * Method to delete one book by index.
     * @param id index of the book to be deleted.
     */
    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    /**
     * Method to save a book to database.
     * @param book Book to be saved.
     * @return Book that is saved.
     */
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Method to update a book in database.
     * @param book Book to be updated.
     * @return Book that is updated.
     */
    public Book update(Book book) {
        return bookRepository.save(book);
    }
}
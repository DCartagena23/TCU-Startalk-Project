package edu.cs.tcu.tcustartalkproject.service;

import edu.cs.tcu.tcustartalkproject.domain.Book;
import edu.cs.tcu.tcustartalkproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(final String id) {
        return bookRepository.findById(id).get();
    }

    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.save(book);
    }
}

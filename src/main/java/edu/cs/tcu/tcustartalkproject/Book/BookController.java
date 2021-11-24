package edu.cs.tcu.tcustartalkproject.Book;

import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    /**
     * Service for basic operations: findAll(), findById(), delete(), save(), update()
     */
    private final BookService bookService;

    /**
     * Constructor for Book Controller
     * @param bookService Book Service supports basic operations.
     */
    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    /**
     * Method to find all books.
     * @return Result object that contains status code, message, and found books.
     */
    @GetMapping("/findAll")
    @ResponseBody
    public Result findAllBooks() {
        final List<Book> books = bookService.findAll();
        return new Result(StatusCode.SUCCESS, "Find All Book Success", books);
    }

    /**
     * Method to find one book.
     * @param id index of the book to be sought.
     * @return Result object that contains status code, message, and found book.
     */
    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getBook(@PathVariable String id) {
        Book book = bookService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Book Success", book);
    }

    /**
     * Method to save a book.
     * @param book Book object to be saved.
     * @return Result object that contains status code, message, and saved book.
     */
    @PostMapping("/saveBook")
    @ResponseBody
    public Result saveBook(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return new Result(StatusCode.SUCCESS, "Book Saved!", savedBook);
    }

    /**
     * Method to update a book.
     * @param book Book object to updated.
     * @return Result object that contains status code, message, and updated book.
     */
    @PutMapping("/updateBook")
    @ResponseBody
    public Result updateBook(@RequestBody Book book) {
        Book updateBook = bookService.update(book);
        return new Result(StatusCode.SUCCESS, "Book Updated!", updateBook);
    }

    /**
     * Method to delete a book.
     * @param id index of the book to be deleted.
     * @return Result object that contains status code and message.
     */
    @DeleteMapping("/deleteBook/{id}")
    @ResponseBody
    public Result deleteBook(@PathVariable String id) {
        bookService.delete(id);
        return new Result(StatusCode.SUCCESS, "Book Deleted!", null);
    }

    /**
     * Method to get chapters of a book.
     * @param id index of the book to get chapters.
     * @return Result object that contains status code, message, and chapters of the book.
     */
    @GetMapping("/getChapters/{id}")
    @ResponseBody
    public Result getChapters(@PathVariable String id) {
        Book book = bookService.findById(id);
        final List<Chapter> chapters = book.getChapter();
        return new Result(StatusCode.SUCCESS, "Get Chapter Success", chapters);
    }
}
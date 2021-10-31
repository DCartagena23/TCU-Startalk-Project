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
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Result findAllBooks() {
        final List<Book> books = bookService.findAll();
        return new Result(StatusCode.SUCCESS, "Find All Book Success", books);
    }

    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getBook(@PathVariable String id) {
        Book book = bookService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Book Success", book);
    }

    @PostMapping
    @ResponseBody
    public Result saveBook(@RequestBody Book book) {
        // TO-DO: Review save and update
        // Why are PUT and POST identical?
        Book savedBook = bookService.save(book);
        return new Result(StatusCode.SUCCESS, "Book Saved!", savedBook);
    }

    @PutMapping
    @ResponseBody
    public Result updateBook(@RequestBody Book book) {
        // TO-DO: Review save and update
        Book updateBook = bookService.save(book);
        return new Result(StatusCode.SUCCESS, "Book Updated!", updateBook);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result deleteBook(@PathVariable String id) {
        bookService.delete(id);
        return new Result(StatusCode.SUCCESS, "Book Deleted!", null);
    }

    @RequestMapping("/getChapters/{id}")
    @ResponseBody
    public Result getChapters(@PathVariable String id) {
        Book book = bookService.findById(id);
        final List<Chapter> chapters = book.getChapter();
        return new Result(StatusCode.SUCCESS, "Get Chapter Success", chapters);
    }
}

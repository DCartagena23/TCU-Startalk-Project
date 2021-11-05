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

    @GetMapping("/findAll")
    @ResponseBody
    public Result findAllBooks() {
        final List<Book> books = bookService.findAll();
        return new Result(StatusCode.SUCCESS, "Find All Book Success", books);
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getBook(@PathVariable String id) {
        Book book = bookService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Book Success", book);
    }

    @PostMapping("/saveBook")
    @ResponseBody
    public Result saveBook(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return new Result(StatusCode.SUCCESS, "Book Saved!", savedBook);
    }

    @PutMapping("/updateBook")
    @ResponseBody
    public Result updateBook(@RequestBody Book book) {
        Book updateBook = bookService.update(book);
        return new Result(StatusCode.SUCCESS, "Book Updated!", updateBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    @ResponseBody
    public Result deleteBook(@PathVariable String id) {
        bookService.delete(id);
        return new Result(StatusCode.SUCCESS, "Book Deleted!", null);
    }

    @GetMapping("/getChapters/{id}")
    @ResponseBody
    public Result getChapters(@PathVariable String id) {
        Book book = bookService.findById(id);
        final List<Chapter> chapters = book.getChapter();
        return new Result(StatusCode.SUCCESS, "Get Chapter Success", chapters);
    }
}

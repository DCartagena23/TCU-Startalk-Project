package edu.cs.tcu.tcustartalkproject.controller;

import edu.cs.tcu.tcustartalkproject.domain.Book;
import edu.cs.tcu.tcustartalkproject.domain.Chapter;
import edu.cs.tcu.tcustartalkproject.domain.Result;
import edu.cs.tcu.tcustartalkproject.domain.StatusCode;
import edu.cs.tcu.tcustartalkproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/findAll")
    @ResponseBody
    public Result findAll() {
        final List<Book> books = bookService.findAll();
        return new Result(StatusCode.SUCCESS, "Find All Success", books);
    }

    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getProduct(@PathVariable String id) {
        Book book = bookService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find One Product Success", book);
    }

    @PutMapping
    @ResponseBody
    public Result update(@RequestBody Book book) {

        Book updateBook = bookService.save(book);

        return new Result(StatusCode.SUCCESS, "Book Updated!", updateBook);
    }

    @PostMapping
    @ResponseBody
    public Result save(@RequestBody Book book) {

        Book savedBook = bookService.save(book);

        return new Result(StatusCode.SUCCESS, "Book Saved!", savedBook);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id) {
        bookService.delete(id);
        return new Result(StatusCode.SUCCESS, "Book Deleted!", null);
    }

    @RequestMapping("/chapter/{id}")
    @ResponseBody
    public Result getChapter(@PathVariable String id) {
        Book book = bookService.findById(id);
        final List<Chapter> chapters = book.getChapter();
        return new Result(StatusCode.SUCCESS, "Get Chapter Success", chapters);
    }
}

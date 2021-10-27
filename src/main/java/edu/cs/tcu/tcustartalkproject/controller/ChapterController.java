package edu.cs.tcu.tcustartalkproject.controller;

import edu.cs.tcu.tcustartalkproject.domain.Book;
import edu.cs.tcu.tcustartalkproject.domain.Chapter;
import edu.cs.tcu.tcustartalkproject.domain.Result;
import edu.cs.tcu.tcustartalkproject.domain.StatusCode;
import edu.cs.tcu.tcustartalkproject.service.BookService;
import edu.cs.tcu.tcustartalkproject.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chapters")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private BookService bookService;


    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getProduct(@PathVariable String id) {
        Chapter chapter = chapterService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find One Product Success", chapter);
    }

    @PutMapping
    @RequestMapping("/{bookId}")
    @ResponseBody
    public Result update(@PathVariable String bookId, @RequestBody Chapter chapter) {
        Book book = bookService.findById(bookId);
        book.addChapter(chapter);
        bookService.save(book);

        return new Result(StatusCode.SUCCESS, "Chapter Updated!", chapter);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id) {
        chapterService.delete(id);
        return new Result(StatusCode.SUCCESS, "Chapter Deleted!", null);
    }
}

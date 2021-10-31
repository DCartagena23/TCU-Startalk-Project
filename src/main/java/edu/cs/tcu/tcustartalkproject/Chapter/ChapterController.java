package edu.cs.tcu.tcustartalkproject.Chapter;

import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Word.GrammarWord;
import edu.cs.tcu.tcustartalkproject.Book.BookService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chapters")
public class ChapterController {
    private final ChapterService chapterService;
    private final BookService bookService;

    @Autowired
    public ChapterController(ChapterService chapterService, BookService bookService){
        this.chapterService = chapterService;
        this.bookService = bookService;
    }


    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getChapter(@PathVariable String id) {
        Chapter chapter = chapterService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Chapter Success", chapter);
    }

    @PutMapping
    @RequestMapping("/{bookId}")
    @ResponseBody
    public Result updateChapter(@PathVariable String bookId, @RequestBody Chapter chapter) {
        // TO-DO: Review update. This method has unclear name. Should it be addChapter or updateChapter?
        // Review the request mapping path
        // Why does the book add a new chapter  every time updating a chapter?
        // Where is POST mapping?
        Book book = bookService.findById(bookId);
        book.addChapter(chapter);
        bookService.save(book);
        return new Result(StatusCode.SUCCESS, "Chapter Updated!", chapter);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result deleteChapter(@PathVariable String id) {
        chapterService.delete(id);
        return new Result(StatusCode.SUCCESS, "Chapter Deleted!", null);
    }

    @RequestMapping("/getGrammarWords/{id}")
    @ResponseBody
    public Result getGrammarWords(@PathVariable String id) {
        Chapter chapter = chapterService.findById(id);
        final List<GrammarWord> grammarWords = chapter.getGrammarWords();
        return new Result(StatusCode.SUCCESS, "Get Grammar Words Success", grammarWords);
    }
}

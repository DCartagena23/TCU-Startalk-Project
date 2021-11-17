package edu.cs.tcu.tcustartalkproject.Chapter;

import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import edu.cs.tcu.tcustartalkproject.Book.BookService;
import edu.cs.tcu.tcustartalkproject.Pinyin.Pinyin;
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


    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getChapter(@PathVariable String id) {
        Chapter chapter = chapterService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Chapter Success", chapter);
    }

    @PostMapping("/saveChapter/{bookId}")
    @ResponseBody
    public Result saveChapter(@PathVariable String bookId, @RequestBody Chapter chapter) {
        Book book = bookService.findById(bookId);
        book.addChapter(chapter);
        bookService.update(book);
        return new Result(StatusCode.SUCCESS, "Chapter Saved!", chapter);
    }

    @PutMapping("/updateChapter/{bookId}")
    @ResponseBody
    public Result updateChapter(@PathVariable String bookId, @RequestBody Chapter chapter) {
        Book book = bookService.findById(bookId);
        book.addChapter(chapter);
        bookService.update(book);
        return new Result(StatusCode.SUCCESS, "Chapter Updated!", chapter);
    }

    @DeleteMapping("/deleteChapter/{id}")
    @ResponseBody
    public Result deleteChapter(@PathVariable String id) {
        chapterService.delete(id);
        return new Result(StatusCode.SUCCESS, "Chapter Deleted!", null);
    }

    @GetMapping("/getGrammarWords/{id}")
    @ResponseBody
    public Result getGrammarWords(@PathVariable String id) {
        Chapter chapter = chapterService.findById(id);
        final List<GrammarWord> grammarWords = chapter.getGrammarWords();
        return new Result(StatusCode.SUCCESS, "Get Grammar Words Success", grammarWords);
    }

    @GetMapping("/getPinyin/{id}")
    @ResponseBody
    public Result getPinyin(@PathVariable String id) {
        Chapter chapter = chapterService.findById(id);
        final List<Pinyin> pinyin = chapter.getPinyin();
        return new Result(StatusCode.SUCCESS, "Get Grammar Words Success", pinyin);
    }
}

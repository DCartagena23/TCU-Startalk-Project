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
    /**
     * Service for basic operations related to chapter: findAll(), findById(), delete(), save(), update()
     */
    private final ChapterService chapterService;

    /**
     * Service for basic operations related to book: findAll(), findById(), delete(), save(), update()
     */
    private final BookService bookService;

    /**
     * Constructor for Chapter Controller
     * @param chapterService Chapter Service supports basic operations related to chapter
     * @param bookService Book Service supports basic operations related to book
     */
    @Autowired
    public ChapterController(ChapterService chapterService, BookService bookService){
        this.chapterService = chapterService;
        this.bookService = bookService;
    }


    /**
     * Method to find one chapter.
     * @param id index of the chapter to be sought.
     * @return Result object that contains status code, message, and found chapter.
     */
    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getChapter(@PathVariable String id) {
        Chapter chapter = chapterService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Chapter Success", chapter);
    }

    /**
     * Method to save a chapter given the index of book.
     * @param bookId index of Book object to have chapter saved.
     * @param chapter Chapter object to be saved.
     * @return Result object that contains status code, message, and saved chapter.
     */
    @PostMapping("/saveChapter/{bookId}")
    @ResponseBody
    public Result saveChapter(@PathVariable String bookId, @RequestBody Chapter chapter) {
        Book book = bookService.findById(bookId);
        book.addChapter(chapter);
        bookService.update(book);
        return new Result(StatusCode.SUCCESS, "Chapter Saved!", chapter);
    }

    /**
     * Method to update a chapter.
     * @param bookId index of Book object to have chapter updated.
     * @param chapter Chapter object to be updated.
     * @return Result object that contains status code, message, and updated chapter
     */
    @PutMapping("/updateChapter/{bookId}")
    @ResponseBody
    public Result updateChapter(@PathVariable String bookId, @RequestBody Chapter chapter) {
        Book book = bookService.findById(bookId);
        book.addChapter(chapter);
        bookService.update(book);
        return new Result(StatusCode.SUCCESS, "Chapter Updated!", chapter);
    }

    /**
     * Method to delete a chapter
     * @param id index of the chapter to be deleted.
     * @return Result object that contains status code and message.
     */
    @DeleteMapping("/deleteChapter/{id}")
    @ResponseBody
    public Result deleteChapter(@PathVariable String id) {
        chapterService.delete(id);
        return new Result(StatusCode.SUCCESS, "Chapter Deleted!", null);
    }

    /**
     * Method to get Grammar Words of a chapter.
     * @param id index of the chapter.
     * @return Result object that contains status code, message, and grammar words of the chapter.
     */
    @GetMapping("/getGrammarWords/{id}")
    @ResponseBody
    public Result getGrammarWords(@PathVariable String id) {
        Chapter chapter = chapterService.findById(id);
        final List<GrammarWord> grammarWords = chapter.getGrammarWords();
        return new Result(StatusCode.SUCCESS, "Get Grammar Words Success", grammarWords);
    }

    /**
     * Method to get pinyins of a chapter.
     * @param id index of the chapter.
     * @return Result object that contains status code, message, and pinyins of the chapter.
     */
    @GetMapping("/getPinyin/{id}")
    @ResponseBody
    public Result getPinyin(@PathVariable String id) {
        Chapter chapter = chapterService.findById(id);
        final List<Pinyin> pinyin = chapter.getPinyin();
        return new Result(StatusCode.SUCCESS, "Get Pinyin Success", pinyin);
    }
}

package edu.cs.tcu.tcustartalkproject;

import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Book.BookController;
import edu.cs.tcu.tcustartalkproject.Book.BookRepository;
import edu.cs.tcu.tcustartalkproject.Book.BookService;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterController;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterService;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWordService;
import edu.cs.tcu.tcustartalkproject.Pinyin.Pinyin;
import edu.cs.tcu.tcustartalkproject.Pinyin.PinyinController;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterRepository;
import edu.cs.tcu.tcustartalkproject.Pinyin.PinyinRepository;
import edu.cs.tcu.tcustartalkproject.Pinyin.PinyinService;
import edu.cs.tcu.tcustartalkproject.VocabWord.VocabWordService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.TextToSpeechController;
import edu.cs.tcu.tcustartalkproject.utils.TokenizerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TcuStartalkProjectApplicationTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VocabWordService vocabWordService;

    @Autowired
    private GrammarWordService grammarWordService;

    @Autowired
    private PinyinService pinyinService;
    // Sample JUnit test
    @Test
    void testLengthTokenize2(){
        Chapter chapter = new Chapter("32f2", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        TokenizerController tokenizerController = new TokenizerController();
        Result result = tokenizerController.tokenize2(chapter);
        ArrayList<String> returnedText = (ArrayList<String>) result.getData();
        ArrayList<String> tokenizedText = new ArrayList<>(Arrays.asList("洁", "面", "仪", "配合", "洁", "面",
                "深层", "清洁", "毛孔", "清洁", "鼻孔", "面膜", "碎", "觉", "使劲", "挤", "才能", "出", "一点点", "皱纹"));
        assertEquals(returnedText.size(), tokenizedText.size());
    }

    @Test
    void testTextToSpeech() throws Exception {
        Chapter chapter = new Chapter("32f2", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        TextToSpeechController textToSpeechController = new TextToSpeechController();
        Result result = textToSpeechController.textToSpeech(chapter);
        assertEquals("TTS Success", result.getMsg());
    }

    @Test
    @Transactional
    void testSaveChapter(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        bookController.saveBook(book);
        Chapter chapter = new Chapter("32f3", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        ChapterController chapterController = new ChapterController(chapterService, bookService);
        Result result = chapterController.saveChapter(book.getId(), chapter);
        assertEquals("Chapter Saved!", result.getMsg());
    }

    @Test
    @Transactional
    void testGetChapter(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        bookController.saveBook(book);
        Chapter chapter = new Chapter("32f3", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        ChapterController chapterController = new ChapterController(chapterService, bookService);
        chapterController.saveChapter(book.getId(), chapter);
        Result result = chapterController.getChapter(chapter.getId());
        assertEquals("Find Chapter Success", result.getMsg());
    }

    @Test
    @Transactional
    void testDeleteChapter(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        bookController.saveBook(book);
        Chapter chapter = new Chapter("32f3", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        ChapterController chapterController = new ChapterController(chapterService, bookService);
        chapterController.saveChapter(book.getId(), chapter);
        Result result = chapterController.deleteChapter(chapter.getId());
        assertEquals("Chapter Deleted!", result.getMsg());
    }

    @Test
    @Transactional
    void testGetGrammarWord(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        bookController.saveBook(book);
        Chapter chapter = new Chapter("32f3", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        ChapterController chapterController = new ChapterController(chapterService, bookService);
        chapterController.saveChapter(book.getId(), chapter);
        Result result = chapterController.getGrammarWords(chapter.getId());
        assertEquals("Get Grammar Words Success", result.getMsg());
    }

    @Test
    @Transactional
    void testChapterGetPinyin(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        bookController.saveBook(book);
        Chapter chapter = new Chapter("32f3", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        ChapterController chapterController = new ChapterController(chapterService, bookService);
        chapterController.saveChapter(book.getId(), chapter);
        Result result = chapterController.getPinyin(chapter.getId());
        assertEquals("Get Pinyin Success", result.getMsg());
    }

    @Test
    void testSavePinyin(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        bookController.saveBook(book);
        Chapter chapter = new Chapter("32f3", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        ChapterController chapterController = new ChapterController(chapterService, bookService);
        chapterController.saveChapter(book.getId(), chapter);
        PinyinController pinyinController = new PinyinController(chapterService, pinyinService);
        Pinyin pinyin = new Pinyin();
        pinyin.setPinyin("Jiémiàn yí pèihé jiémiàn shēncéng qīngjié máokǒng qīngjié bíkǒng miànmó " +
                "suì jué shǐjìn jǐ cáinéng chū yī diǎndiǎn zhòuwén");
        Result result = pinyinController.savePinyin(chapter.getId(), pinyin);
        assertEquals(pinyin, result.getData());
    }

    @Test
    void testGetPinyin(){
        Chapter chapter = new Chapter("32f2", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        PinyinController pinyinController = new PinyinController(chapterService, pinyinService);
        Result result = pinyinController.getPinyin(chapter.getId());
        assertEquals("Find One Pinyin Success", result.getMsg());
    }

    /*@Test
    void testDeletePinyin(){
        Chapter chapter = new Chapter("32f2", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        PinyinController pinyinController = new PinyinController(chapterService, pinyinService);
        Result result = pinyinController.deletePinyin(chapter.getId());
        assertEquals(null, result.getData());
    }*/

    @Test
    void testDeleteBook(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        bookController.saveBook(book);
        Result result = bookController.deleteBook(book.getId());
        assertEquals(null, result.getData());
    }

    @Test
    void testSaveBook(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        Result result = bookController.saveBook(book);
        assertEquals("Book Saved!", result.getMsg());
    }

    @Test
    void testGetChapters(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        bookController.saveBook(book);
        Result result = bookController.getChapters(book.getId());
        assertEquals("Get Chapter Success", result.getMsg());
    }

    @Test
    void testFindAllBooks(){
        BookController bookController = new BookController(bookService);
        Result result = bookController.findAllBooks();
        assertEquals("Find All Book Success", result.getMsg());
    }

    @Test
    void testGetBook(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        bookController.saveBook(book);
        Result result = bookController.getBook(book.getId());
        assertEquals("Find Book Success", result.getMsg());
    }

    @Test
    void testUpdateBook(){
        Book book = new Book("32f2", "Harry Potter", "", "J.K. Rowling", 10.50);
        BookController bookController = new BookController(bookService);
        bookController.saveBook(book);
        book.setDesc("Harry goes to a school of wizards.");
        Result result = bookController.updateBook(book);
        assertEquals("Book Updated!", result.getMsg());
    }


}

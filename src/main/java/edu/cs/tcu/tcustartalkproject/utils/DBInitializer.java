package edu.cs.tcu.tcustartalkproject.utils;

import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Book.BookService;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterService;
import edu.cs.tcu.tcustartalkproject.Chapter.Pinyin;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWordService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBInitializer implements CommandLineRunner {
    private final BookService bookService;
    private final ChapterService chapterService;
    private final GrammarWordService grammarWordService;

    @Autowired
    public DBInitializer(BookService bookService, ChapterService chapterService, GrammarWordService grammarWordService){
        this.bookService = bookService;
        this.chapterService = chapterService;
        this.grammarWordService = grammarWordService;
    }

    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Book b1 = new Book(new ObjectId().toHexString(),"Harry Potter and the Sorcerer’s Stone", "Bla","JKR", 35.99);
        Book b2 = new Book(new ObjectId().toHexString(),"Harry Potter and the Chamber of Secrets", "Bla","JKR", 35.99);
        Book b3 = new Book(new ObjectId().toHexString(),"Harry Potter and the Prisoner of Azkaban", "Bla","JKR", 35.99);
        Book b4 = new Book(new ObjectId().toHexString(),"Harry Potter and the Goblet of Fire", "Bla","JKR", 35.99);
        Book b5 = new Book(new ObjectId().toHexString(),"Harry Potter and the Order of the Phoenix", "Bla","JKR", 35.99);
        Book b6 = new Book(new ObjectId().toHexString(),"Harry Potter and the Half-Blood Prince", "Bla","JKR", 35.99);
        Book b7 = new Book(new ObjectId().toHexString(),"Harry Potter and the Deathly Hallows", "Bla","JKR", 35.99);

        Chapter c1 = new Chapter(new ObjectId().toHexString(), 1,"Name 1");
        Chapter c2 = new Chapter(new ObjectId().toHexString(), 2,"Name 2");
        Chapter c3 = new Chapter(new ObjectId().toHexString(),1,"Name 3");
        Chapter c4 = new Chapter(new ObjectId().toHexString(),2,"Name 4");

        String str1 = "洁面仪配合洁面深层清洁毛孔 清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹 脸颊毛孔修复的看不见啦 草莓鼻历史遗留问题没辙 脸和脖子差不多颜色的皮肤才是健康的 长期使用安全健康的比同龄人显小五到十岁 28岁的妹子看看你们的鱼尾纹";
        String str2 = "亂數 假 文 產 生器 。\n亂數 假 文 產 生器 。";

        c1.setTextFromString(str1);
        c2.setTextFromString(str2);

        Pinyin pinyin1 = new Pinyin();
        pinyin1.setId("飒");
        pinyin1.setPinyin("sà");

        Pinyin pinyin2 = new Pinyin();
        pinyin2.setId("体会");
        pinyin2.setPinyin("tǐhuì");

        List<Pinyin> pinyinList = new ArrayList<Pinyin>();
        pinyinList.add(pinyin1);
        pinyinList.add(pinyin2);
        c1.setPinyin(pinyinList);

        b1.addChapter(c1);
        b1.addChapter(c2);
        b2.addChapter(c3);
        b2.addChapter(c4);

        GrammarWord grammarWord1 = new GrammarWord();
        grammarWord1.setId(new ObjectId().toHexString());
        grammarWord1.setWord("据说");
        grammarWord1.setDesc("据说 It is said; According to; Based on<br/>" +
                "据说 is used at the beginning of a sentence to express a source of information. If the information " +
                "source is specified, we have to split 据 and 说and put it in between .");

        GrammarWord grammarWord2 = new GrammarWord();
        grammarWord2.setId(new ObjectId().toHexString());
        grammarWord2.setWord("多少");
        grammarWord2.setDesc("多少 here does not function as a question word, but an adverb meaning \"more or less, " +
                "somewhat, to some extent.\" It must be placed after the subject and before the verb in the " +
                "sentence. Expressions denoting quantity such as: 些 or 一点儿, are often used after the verb.");

        GrammarWord grammarWord3 = new GrammarWord();
        grammarWord3.setId(new ObjectId().toHexString());
        grammarWord3.setWord("（比方说/像）X啊，Y啊。");
        grammarWord3.setDesc("This pattern is used to list more than one example of the topic. The particle \"M\" is" +
                "used more frequently in speaking.");

        c1.addGrammarWords(grammarWord1);
        c1.addGrammarWords(grammarWord2);
        c1.addGrammarWords(grammarWord3);


        bookService.save(b1);
        bookService.save(b2);
        bookService.save(b3);
        bookService.save(b4);
        bookService.save(b5);
        bookService.save(b6);
        bookService.save(b7);

        chapterService.save(c1);
        chapterService.save(c2);
        chapterService.save(c3);
        chapterService.save(c4);

        grammarWordService.save(grammarWord1);
        grammarWordService.save(grammarWord2);
        grammarWordService.save(grammarWord3);
    }
}
package edu.cs.tcu.tcustartalkproject.utils;

import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Book.BookService;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterService;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWordService;
import edu.cs.tcu.tcustartalkproject.VocabWord.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VocabWordService vocabWordService;

    @Autowired
    private GrammarWordService grammarWordService;

    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Book b1 = new Book(UUID.randomUUID().toString(),"Harry Potter and the Sorcerer’s Stone", "Bla","JKR", 35.99);
        Book b2 = new Book(UUID.randomUUID().toString(),"Harry Potter and the Chamber of Secrets", "Bla","JKR", 35.99);
        Book b3 = new Book(UUID.randomUUID().toString(),"Harry Potter and the Prisoner of Azkaban", "Bla","JKR", 35.99);
        Book b4 = new Book(UUID.randomUUID().toString(),"Harry Potter and the Goblet of Fire", "Bla","JKR", 35.99);
        Book b5 = new Book(UUID.randomUUID().toString(),"Harry Potter and the Order of the Phoenix", "Bla","JKR", 35.99);
        Book b6 = new Book(UUID.randomUUID().toString(),"Harry Potter and the Half-Blood Prince", "Bla","JKR", 35.99);
        Book b7 = new Book(UUID.randomUUID().toString(),"Harry Potter and the Deathly Hallows", "Bla","JKR", 35.99);

        Chapter c1 = new Chapter(UUID.randomUUID().toString(), 1,"Name 1");
        Chapter c2 = new Chapter(UUID.randomUUID().toString(), 2,"Name 2");
        Chapter c3 = new Chapter(UUID.randomUUID().toString(),1,"Name 3");
        Chapter c4 = new Chapter(UUID.randomUUID().toString(),2,"Name 4");

        String str1 = "洁面仪配合洁面深层清洁毛孔 清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹 脸颊毛孔修复的看不见啦 草莓鼻历史遗留问题没辙 脸和脖子差不多颜色的皮肤才是健康的 长期使用安全健康的比同龄人显小五到十岁 28岁的妹子看看你们的鱼尾纹";
        String str2 = "亂數 假 文 產 生器 。\n亂數 假 文 產 生器 。";

        c1.setTextFromString(str1);
        c2.setTextFromString(str2);

        b1.addChapter(c1);
        b1.addChapter(c2);
        b2.addChapter(c3);
        b2.addChapter(c4);

        bookService.save(b1);
        bookService.save(b2);
        bookService.save(b3);
        bookService.save(b4);
        bookService.save(b5);
        bookService.save(b6);
        bookService.save(b7);

        GrammarWord grammarWord1 = new GrammarWord();
        grammarWord1.setId(UUID.randomUUID().toString());
        grammarWord1.setWord("据说");
        grammarWord1.setDesc("据说 It is said; According to; Based on<br/>" +
                "据说 is used at the beginning of a sentence to express a source of information. If the information " +
                "source is specified, we have to split 据 and 说and put it in between .");

        GrammarWord grammarWord2 = new GrammarWord();
        grammarWord2.setId(UUID.randomUUID().toString());
        grammarWord2.setWord("多少");
        grammarWord2.setDesc("多少 here does not function as a question word, but an adverb meaning \"more or less, " +
                "somewhat, to some extent.\" It must be placed after the subject and before the verb in the " +
                "sentence. Expressions denoting quantity such as: 些 or 一点儿, are often used after the verb.");

        GrammarWord grammarWord3 = new GrammarWord();
        grammarWord3.setId(UUID.randomUUID().toString());
        grammarWord3.setWord("（比方说/像）X啊，Y啊。");
        grammarWord3.setDesc("This pattern is used to list more than one example of the topic. The particle \"M\" is" +
                "used more frequently in speaking.");

        c1.addGrammarWords(grammarWord1);
        c1.addGrammarWords(grammarWord2);
        c1.addGrammarWords(grammarWord3);

        grammarWordService.save(grammarWord1);
        grammarWordService.save(grammarWord2);
        grammarWordService.save(grammarWord3);

        VocabWord vocabWord1 = new VocabWord();
        vocabWord1.setId(UUID.randomUUID().toString());
        vocabWord1.setWord("单纯");
        vocabWord1.setPinyin("Dānchún");
        vocabWord1.setDesc("Definition: Simple");

        VocabWord vocabWord2 = new VocabWord();
        vocabWord2.setId(UUID.randomUUID().toString());
        vocabWord2.setWord("观念");
        vocabWord2.setPinyin("Guānniàn");
        vocabWord2.setDesc("Definition: Idea");

        VocabWord vocabWord3 = new VocabWord();
        vocabWord3.setId(UUID.randomUUID().toString());
        vocabWord3.setWord("社会 制度");
        vocabWord3.setPinyin("Shèhuì zhìdù");
        vocabWord3.setDesc("Definition: Social system");

        VocabWord vocabWord4 = new VocabWord();
        vocabWord4.setId(UUID.randomUUID().toString());
        vocabWord4.setWord("经济");
        vocabWord4.setPinyin("Jīngjì");
        vocabWord4.setDesc("Definition: Economy");

        VocabWord vocabWord5 = new VocabWord();
        vocabWord5.setId(UUID.randomUUID().toString());
        vocabWord5.setWord("挤");
        vocabWord5.setPinyin("Jǐ");
        vocabWord5.setDesc("Definition: Squeeze");

        VocabWord vocabWord6 = new VocabWord();
        vocabWord6.setId(UUID.randomUUID().toString());
        vocabWord6.setWord("保持");
        vocabWord6.setPinyin("Bǎochí");
        vocabWord6.setDesc("Definition: Keep");

        vocabWordService.save(vocabWord1);
        vocabWordService.save(vocabWord2);
        vocabWordService.save(vocabWord3);
        vocabWordService.save(vocabWord4);
        vocabWordService.save(vocabWord5);
        vocabWordService.save(vocabWord6);
    }
}
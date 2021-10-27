package edu.cs.tcu.tcustartalkproject.datainitializer;

import edu.cs.tcu.tcustartalkproject.domain.Book;
import edu.cs.tcu.tcustartalkproject.domain.Chapter;
import edu.cs.tcu.tcustartalkproject.domain.GrammarWord;
import edu.cs.tcu.tcustartalkproject.domain.Word;
import edu.cs.tcu.tcustartalkproject.service.BookService;
import edu.cs.tcu.tcustartalkproject.service.ChapterService;
import edu.cs.tcu.tcustartalkproject.service.GrammarWordService;
import edu.cs.tcu.tcustartalkproject.service.WordService;
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
    private WordService wordService;

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

        Chapter c1 = new Chapter(UUID.randomUUID().toString(), 1,"Name 1","亂數假文產生器。\n亂數假文產生器。");
        Chapter c2 = new Chapter(UUID.randomUUID().toString(), 2,"Name 2","亂數假文產生器。\n亂數假文產生器。");
        Chapter c3 = new Chapter(UUID.randomUUID().toString(),1,"Name 3","");
        Chapter c4 = new Chapter(UUID.randomUUID().toString(),2,"Name 4","");

        Word w1 = new Word("亂");

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

        wordService.save(w1);

        GrammarWord grammarWord1 = new GrammarWord();
        grammarWord1.setId("123");
        grammarWord1.setWord("据说");
        grammarWord1.setDesc("据说 It is said; According to; Based on<br/>" +
                "据说 is used at the beginning of a sentence to express a source of information. If the information " +
                "source is specified, we have to split 据 and 说and put it in between .");

        GrammarWord grammarWord2 = new GrammarWord();
        grammarWord2.setId("124");
        grammarWord2.setWord("多少");
        grammarWord2.setDesc("多少 here does not function as a question word, but an adverb meaning \"more or less, " +
                "somewhat, to some extent.\" It must be placed after the subject and before the verb in the " +
                "sentence. Expressions denoting quantity such as: 些 or 一点儿, are often used after the verb.");

        GrammarWord grammarWord3 = new GrammarWord();
        grammarWord3.setId("125");
        grammarWord3.setWord("（比方说/像）X啊，Y啊。");
        grammarWord3.setDesc("This pattern is used to list more than one example of the topic. The particle \"M\" is" +
                "used more frequently in speaking.");
        grammarWordService.save(grammarWord1);
        grammarWordService.save(grammarWord2);
        grammarWordService.save(grammarWord3);

    }
}

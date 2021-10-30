package edu.cs.tcu.tcustartalkproject.datainitializer;

import edu.cs.tcu.tcustartalkproject.domain.*;
import edu.cs.tcu.tcustartalkproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private VocabWordService vocabWordService;

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
        GrammarWord gw1 = new GrammarWord(UUID.randomUUID().toString(),"亂數","Meaning 1");
        GrammarWord gw2 = new GrammarWord(UUID.randomUUID().toString(),"生器","Meaning 2");

        c1.addGrammarWords(gw1);
        c1.addGrammarWords(gw2);

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
        grammarWordService.save(grammarWord1);
        grammarWordService.save(grammarWord2);
        grammarWordService.save(grammarWord3);

        VocabWord vocabWord1 = new VocabWord();
        vocabWord1.setId("201");
        vocabWord1.setWord("单纯");
        vocabWord1.setDesc("单纯 \n Definition: Simple \n Pinyin: Dānchún");

        VocabWord vocabWord2 = new VocabWord();
        vocabWord2.setId("202");
        vocabWord2.setWord("观念");
        vocabWord2.setDesc("观念 \n Definition: Idea \n Pinyin: Guānniàn");

        VocabWord vocabWord3 = new VocabWord();
        vocabWord3.setId("203");
        vocabWord3.setWord("社会 制度");
        vocabWord3.setDesc("社会 制度 \n Definition: Social system \n Pinyin: Shèhuì zhìdù");

        VocabWord vocabWord4 = new VocabWord();
        vocabWord4.setId("204");
        vocabWord4.setWord("经济");
        vocabWord4.setDesc("经济 \n Definition: Economy \n Pinyin: Jīngjì");

        VocabWord vocabWord5 = new VocabWord();
        vocabWord5.setId("205");
        vocabWord5.setWord("挤");
        vocabWord5.setDesc("挤 \n Definition: Squeeze \n Pinyin: Jǐ");

        VocabWord vocabWord6 = new VocabWord();
        vocabWord6.setId("206");
        vocabWord6.setWord("保持");
        vocabWord6.setDesc("保持 \n Definition: Keep \n Pinyin: Bǎochí");

        VocabWord vocabWord7 = new VocabWord();
        vocabWord7.setId("207");
        vocabWord7.setWord("隐私");
        vocabWord7.setDesc("隐私 \n Definition: Privacy \n Pinyin: Yǐnsī");

        VocabWord vocabWord8 = new VocabWord();
        vocabWord8.setId("208");
        vocabWord8.setWord("单位");
        vocabWord8.setDesc("单位 \n Definition: Unit \n Pinyin: Dānwèi");

        VocabWord vocabWord9 = new VocabWord();
        vocabWord9.setId("209");
        vocabWord9.setWord("年龄");
        vocabWord9.setDesc("年龄 \n Definition: Age \n Pinyin: Niánlíng");

        VocabWord vocabWord10 = new VocabWord();
        vocabWord10.setId("210");
        vocabWord10.setWord("学历");
        vocabWord10.setDesc("学历 \n Definition: Education \n Pinyin: Xuélì");

        VocabWord vocabWord11 = new VocabWord();
        vocabWord11.setId("211");
        vocabWord11.setWord("秘密");
        vocabWord11.setDesc("秘密 \n Definition: Secret \n Pinyin: Mìmì");

        VocabWord vocabWord12 = new VocabWord();
        vocabWord12.setId("212");
        vocabWord12.setWord("公开");
        vocabWord12.setDesc("公开 \n Definition: Public \n Pinyin: Gōngkāi");


        vocabWordService.save(vocabWord1);
        vocabWordService.save(vocabWord2);
        vocabWordService.save(vocabWord3);
        vocabWordService.save(vocabWord4);
        vocabWordService.save(vocabWord5);
        vocabWordService.save(vocabWord6);
        vocabWordService.save(vocabWord7);
        vocabWordService.save(vocabWord8);
        vocabWordService.save(vocabWord9);
        vocabWordService.save(vocabWord10);
        vocabWordService.save(vocabWord11);
        vocabWordService.save(vocabWord12);

    }
}

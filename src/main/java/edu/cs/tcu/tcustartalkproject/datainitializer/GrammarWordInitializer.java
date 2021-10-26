package edu.cs.tcu.tcustartalkproject.datainitializer;

import edu.cs.tcu.tcustartalkproject.dao.GrammarWordDao;
import edu.cs.tcu.tcustartalkproject.domain.GrammarWord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GrammarWordInitializer implements CommandLineRunner {
    private GrammarWordDao grammarWordDao;

    public GrammarWordInitializer(GrammarWordDao grammarWordDao){
        this.grammarWordDao = grammarWordDao;
    }

    @Override
    public void run(String... args) throws Exception {
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

        grammarWordDao.save(grammarWord1);
        grammarWordDao.save(grammarWord2);
        grammarWordDao.save(grammarWord3);
    }
}

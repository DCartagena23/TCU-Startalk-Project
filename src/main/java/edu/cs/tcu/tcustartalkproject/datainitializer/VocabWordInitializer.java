package edu.cs.tcu.tcustartalkproject.datainitializer;

import edu.cs.tcu.tcustartalkproject.dao.VocabWordDao;
import edu.cs.tcu.tcustartalkproject.domain.VocabWord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class VocabWordInitializer implements CommandLineRunner {
    private VocabWordDao vocabWordDao;

    public VocabWordInitializer(VocabWordDao vocabWordDao){
        this.vocabWordDao = vocabWordDao;
    }

    @Override
    public void run(String... args) throws Exception {
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


        vocabWordDao.save(vocabWord1);
        vocabWordDao.save(vocabWord2);
        vocabWordDao.save(vocabWord3);
        vocabWordDao.save(vocabWord4);
        vocabWordDao.save(vocabWord5);
        vocabWordDao.save(vocabWord6);
        vocabWordDao.save(vocabWord7);
        vocabWordDao.save(vocabWord8);
        vocabWordDao.save(vocabWord9);
        vocabWordDao.save(vocabWord10);
        vocabWordDao.save(vocabWord11);
        vocabWordDao.save(vocabWord12);
    }
}


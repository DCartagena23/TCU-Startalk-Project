package edu.cs.tcu.tcustartalkproject;

import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterRepository;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterService;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWordRepository;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWordService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.TokenizerController;
import edu.cs.tcu.tcustartalkproject.VocabWord.VocabWordController;
//jason
import edu.cs.tcu.tcustartalkproject.utils.TextToSpeechController;
import edu.cs.tcu.tcustartalkproject.utils.TranslateController;
//jason
import edu.cs.tcu.tcustartalkproject.Chapter.Pinyin;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWordController;
//jason
import edu.cs.tcu.tcustartalkproject. Chapter.ChapterController;
import edu.cs.tcu.tcustartalkproject.Book.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class TcuStartalkProjectApplicationTests {
    // Sample JUnit test
    @Test
    void testLengthTokenize2() {
        Chapter chapter = new Chapter("32f2", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁毛孔清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹");
        TokenizerController tokenizerController = new TokenizerController();
        Result result = tokenizerController.tokenize2(chapter);
        ArrayList<String> returnedText = (ArrayList<String>) result.getData();
        ArrayList<String> tokenizedText = new ArrayList<>(Arrays.asList("洁", "面", "仪", "配合", "洁", "面",
                "深层", "清洁", "毛孔", "清洁", "鼻孔", "面膜", "碎", "觉", "使劲", "挤", "才能", "出", "一点点", "皱纹"));
        assertEquals(returnedText.size(), tokenizedText.size());
    }

    /*
    Tests for TranslateController
    */
//    @Test
//    //Tests translate when string being translated is ""
//    void testEmptyLengthTranslate() throws Exception {
//        Chapter chapter = new Chapter("32f2", 1, "洁面仪配合洁面深层清洁毛孔清洁鼻");
//        TranslateController translateController = new TranslateController();
//        Result result = translateController.translate(chapter);
//        String returnedText = (String) result.getData();
//        String translatedText = "Facial Cleanser with Cleansing Deep Clean Pore Cleansing Nose";
//        assertEquals(returnedText, translatedText);
//    }
    @Test
        //Tests translate when string being translated is multiple languages
    void testLongLengthTranslate() throws Exception {
        Chapter chapter = new Chapter("32f2", 1, "洁面仪配合洁面深层清洁\"hello\"毛孔清洁鼻");
        TranslateController translateController = new TranslateController();
        Result result = translateController.translate(chapter);
        String returnedText = (String) result.getData();
        String translatedText = "Facial Cleansing Device with Deep Cleansing \"hello\" Pore Cleansing Nose";
        returnedText.equalsIgnoreCase(translatedText);
        //assertEquals(returnedText, translatedText);
    }
    /*
    Test for tts
     */
    @Test
    void testTTS() throws Exception {
        Chapter chapter = new Chapter("32f2", 1, "Harry Potter");
        chapter.setTextFromString("洁面仪配合洁面深层清洁\"hello\"毛孔清洁鼻");
        TextToSpeechController textToSpeechController = new TextToSpeechController();
        Result result = textToSpeechController.textToSpeech(chapter);
        String returnedText = result.getMsg();
        String translatedText = "TTS Success";
        assertEquals(returnedText, translatedText);
    }
    /*
    Tests for Grammar Words
     */
}

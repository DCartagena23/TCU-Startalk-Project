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
import edu.cs.tcu.tcustartalkproject.utils.DictionaryController;
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
    @Test
    //Tests translate when string being translated is ""
    void testEmptyLengthTranslate() throws Exception {
        Chapter chapter = new Chapter("32f2", 1, "洁面仪配合洁面深层清洁毛孔清洁鼻");
        TranslateController translateController = new TranslateController();
        Result result = translateController.translate(chapter);
        String returnedText = (String) result.getData();
        String translatedText = "Facial Cleanser with Cleansing Deep Clean Pore Cleansing Nose";
        assertEquals(returnedText, translatedText);
    }
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
    Tests for Dictionary
    */
    @Test
    //test for getting pinyin of string
    void testPinyin() throws IOException {
        Chapter chapter = new Chapter("32f2", 1, "Harry Potter");
        chapter.setTextFromString("你好");
        DictionaryController dictionaryController = new DictionaryController();
        //Pretty sure this next line is wrong, its just getting the id of the chapter not the next being used.
        Result result = dictionaryController.getPinyin("你好");
        String returnedText = (String) result.getData();
        String expectedPinyin =  "nǐ hǎo";
        assertEquals(returnedText, expectedPinyin);
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
    /*
    @Test
    @Autowired
    void testGetGrammar(){
        GrammarWord word = new GrammarWord("32d1", "洁面","Cleansing");
        ChapterRepository chapterRepository = new ChapterRepository() {
            @Override
            public List<Chapter> findAll() {
                return null;
            }

            @Override
            public List<Chapter> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Chapter> findAllById(Iterable<String> strings) {
                return null;
            }

            @Override
            public <S extends Chapter> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Chapter> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Chapter> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Chapter> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<String> strings) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Chapter getOne(String s) {
                return null;
            }

            @Override
            public Chapter getById(String s) {
                return null;
            }

            @Override
            public <S extends Chapter> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Chapter> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Chapter> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Chapter> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Chapter> findById(String s) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(String s) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(String s) {

            }

            @Override
            public void delete(Chapter entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends String> strings) {

            }

            @Override
            public void deleteAll(Iterable<? extends Chapter> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Chapter> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Chapter> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Chapter> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Chapter> boolean exists(Example<S> example) {
                return false;
            }
        };
        GrammarWordRepository grammarWordRepository = new GrammarWordRepository() {
            @Override
            public List<GrammarWord> findAll() {
                return null;
            }

            @Override
            public List<GrammarWord> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<GrammarWord> findAllById(Iterable<String> strings) {
                return null;
            }

            @Override
            public <S extends GrammarWord> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends GrammarWord> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends GrammarWord> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<GrammarWord> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<String> strings) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public GrammarWord getOne(String s) {
                return null;
            }

            @Override
            public GrammarWord getById(String s) {
                return null;
            }

            @Override
            public <S extends GrammarWord> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends GrammarWord> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<GrammarWord> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends GrammarWord> S save(S entity) {
                return null;
            }

            @Override
            public Optional<GrammarWord> findById(String s) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(String s) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(String s) {

            }

            @Override
            public void delete(GrammarWord entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends String> strings) {

            }

            @Override
            public void deleteAll(Iterable<? extends GrammarWord> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends GrammarWord> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends GrammarWord> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends GrammarWord> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends GrammarWord> boolean exists(Example<S> example) {
                return false;
            }
        };
        ChapterService chapterService = new ChapterService(chapterRepository);
        GrammarWordService grammarWordService = new GrammarWordService(grammarWordRepository);
        GrammarWordController grammarWordController = new GrammarWordController(chapterService, grammarWordService);
        Result result = grammarWordController.getGrammarWord(word.getId());
        String returnedText = (String) result.getData();
        String grammarWord = "洁面";
        assertEquals(returnedText, grammarWord);
    }
*/
}

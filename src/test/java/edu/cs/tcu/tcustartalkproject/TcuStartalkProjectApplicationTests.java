package edu.cs.tcu.tcustartalkproject;

import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.TokenizerController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class TcuStartalkProjectApplicationTests {
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
}

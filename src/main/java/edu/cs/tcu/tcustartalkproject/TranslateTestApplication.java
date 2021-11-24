package edu.cs.tcu.tcustartalkproject;
import edu.cs.tcu.tcustartalkproject.utils.TranslateController;

public class TranslateTestApplication {

    public static void main(String... args) throws Exception {
        String projectId = "singular-citron-331602";
        String targetLanguage = "en";
        String text = "其实中国人觉得问这些问题并不是想发现什么秘密，而是平常谈话和表示关心的一种方式。";
        TranslateController.translateText(projectId, targetLanguage, text);

    }
}

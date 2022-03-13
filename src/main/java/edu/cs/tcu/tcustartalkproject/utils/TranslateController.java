package edu.cs.tcu.tcustartalkproject.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.cloud.translate.v3.*;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.SetEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TranslateController {
    private String projectId = "tcu-startalk";
    private String targetLanguage = "en";

    @Autowired
    public TranslateController(){
        this.projectId = "tcu-startalk";
        this.targetLanguage = "en";
    }

    @PostMapping("/translate")
    @ResponseBody
    public Result translate(@RequestBody Chapter chapter) throws Exception {
        String string = chapter.getTitle();

//        Map<String, String> google = new HashMap<>();
//        google.put("GOOGLE_APPLICATION_CREDENTIALS",
//                new ClassPathResource("tcu-startalk-0bb06d8d08e1.json").getURI().getPath());
//        SetEnv.setEnv(google);

        String translate = TranslateController.translateText(projectId, targetLanguage, string);

        return new Result(StatusCode.SUCCESS, "Translate Success", translate);
    }

    // Translate text to target language.
    public static String translateText(String projectId, String targetLanguage, String text) throws IOException {
        String translate = "";
        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            // Supported Locations: `global`, [glossary location], or [model location]
            // Glossaries must be hosted in `us-central1`
            // Custom Models must use the same location as your model. (us-central1)
            LocationName parent = LocationName.of(projectId, "global");

            // Supported Mime Types: https://cloud.google.com/translate/docs/supported-formats
            TranslateTextRequest request =
                    TranslateTextRequest.newBuilder()
                            .setParent(parent.toString())
                            .setMimeType("text/plain")
                            .setTargetLanguageCode(targetLanguage)
                            .addContents(text)
                            .build();

            TranslateTextResponse response = client.translateText(request);

            // Display the translation for each input text provided
            for (Translation translation : response.getTranslationsList()) {
                translate = translate + translation.getTranslatedText() + "\n";
            }
        }
        return translate;
    }
}
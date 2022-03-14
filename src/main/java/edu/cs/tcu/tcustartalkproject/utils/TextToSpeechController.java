package edu.cs.tcu.tcustartalkproject.utils;

import com.google.cloud.texttospeech.v1beta1.*;
import com.google.protobuf.ByteString;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.SetEnv;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TextToSpeechController {

    @PostMapping("/tts")
    @ResponseBody
    public Result textToSpeech(@RequestBody Chapter chapter) throws Exception {
        Map<String, String> google = new HashMap<>();
        google.put("GOOGLE_APPLICATION_CREDENTIALS",
                new ClassPathResource("singular-citron-331602-3b396d6f7bf9.json").getURI().getPath());
        SetEnv.setEnv(google);

        String string = chapter.getTitle();

        // Instantiates a client
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText(string).build();
            // Build the voice request,
            // select the language code and the ssml voice gender
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("cmn-CN").setSsmlGender(SsmlVoiceGender.NEUTRAL).build();
            // Select the type of audio file you want returned (MP3)
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3).build();
            // Perform the text-to-speech request on the text input with the selected voice parameters
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            // Get the audio contents from the response
            ByteString audioContents = response.getAudioContent();


            byte[] file = audioContents.toByteArray();

            return new Result(StatusCode.SUCCESS, "TTS Success", file);
        }
    }
    /*
    public static Result texttospeech(@RequestBody Chapter Chapter) {
        String string = chapter.getTitle();
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText(string).build();
            // Build the voice request,
            // select the language code and the ssml voice gender
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("cmn-CN").setSsmlGender(SsmlVoiceGender.NEUTRAL).build();
            // Select the type of audio file you want returned (MP3)
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3).build();
            // Perform the text-to-speech request on the text input with the selected voice parameters
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            // Get the audio contents from the response
            ByteString audioContents = response.getAudioContent();


            byte[] file = audioContents.toByteArray();
        }
        return file;
    }

     */
}

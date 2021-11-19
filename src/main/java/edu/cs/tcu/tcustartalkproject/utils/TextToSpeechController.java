package edu.cs.tcu.tcustartalkproject.utils;

import com.google.cloud.texttospeech.v1beta1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Controller
public class TextToSpeechController {

    @PostMapping("/tts")
    @ResponseBody
    public Result textToSpeech(@RequestBody String string) throws Exception {
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
            // Write the response to the output file.
            try (OutputStream outfile = new FileOutputStream("./startalk_frontend/src/assets/tts.mp3")) {
                outfile.write(audioContents.toByteArray());
                System.out.println("Audio content written to file \"output.mp3\"");
                return new Result(StatusCode.SUCCESS, "TTS Success", outfile);
            }
        }
    }
}

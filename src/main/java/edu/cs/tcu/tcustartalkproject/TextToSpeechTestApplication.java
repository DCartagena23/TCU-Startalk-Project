package edu.cs.tcu.tcustartalkproject;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.google.cloud.texttospeech.v1beta1.*;
import com.google.protobuf.ByteString;
import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

public class TextToSpeechTestApplication {

    public static void main(String... args) throws Exception {
        Map<String, String> google = new HashMap<>();
        google.put("GOOGLE_APPLICATION_CREDENTIALS",
                new ClassPathResource("singular-citron-331602-3b396d6f7bf9.json").getURI().getPath());
        SetEnv.setEnv(google);
        // Instantiates a client
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText("'隐私'这个词虽然有点儿像privacy，但是中国人不常说，而且“隐私”这个词，多少有些不好的意思，所以有人说中 国人没有privacy的观念").build();
            // Build the voice request, select the language code ("en-US") and the ssml
            // voice gender
            // ("neutral")
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode("cmn-CN")
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL).setName("cmn-CN-Wavenet-A").build();
            // Select the type of audio file you want returned
            AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).setSpeakingRate(1.0).build();
            // Perform the text-to-speech request on the text input with the selected voice
            // parameters and
            // audio file type
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            // Get the audio contents from the response
            ByteString audioContents = response.getAudioContent();
            // Write the response to the output file.
            try (OutputStream out = new FileOutputStream("./Vue Frontend/src/assets/tts_test.mp3")) {
                out.write(audioContents.toByteArray());
                System.out.println("Audio content written to file \"output.mp3\"");
            }
        }
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAUYJLXQWAUKS6IVF6",
                "F17P4YPBBxs02cEG6MtW3T0aptgQrkMmiTKCs");
        AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
        List<Bucket> buckets = s3client.listBuckets();
        String name = null;
        for (Bucket bucket : buckets) {
            name = bucket.getName();
        }
        s3client.putObject(
                name,
                "assets/tts_test.mp3",
                new File("./Vue Frontend/src/assets/tts_test.mp3")
        );
    }
}

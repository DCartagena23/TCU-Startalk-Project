package edu.cs.tcu.tcustartalkproject.utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import edu.cs.tcu.tcustartalkproject.AudioTest.AudioAnswer;
import edu.cs.tcu.tcustartalkproject.AudioTest.AudioTest;
import edu.cs.tcu.tcustartalkproject.AudioTest.AudioTestRepository;
import edu.cs.tcu.tcustartalkproject.AudioTest.AudioTestService;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.Pinyin;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@RestController
@RequestMapping("/storage/")
public class S3RestController {
    private AmazonS3 s3client;
    private final AudioTestService testService;

    @Value("${s3bucket}")
    private String bucketname;

    @Autowired
    S3RestController(AudioTestService testService) throws FileNotFoundException {

        this.testService = testService;
//        Scanner sc = new Scanner(new File("src/main/resources/rootkey.csv"));
//        sc.useDelimiter("=|\\n");   //sets the delimiter pattern
//        String[] accessKey = new String[4];
//        for (int i = 0; sc.hasNext() != false; i++){
//            accessKey[i] = sc.next();
//        }
//        sc.close();  //closes the scanner
//        AWSCredentials credentials = new BasicAWSCredentials(
//                accessKey[1],
//                accessKey[3]);
//        this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
//        System.out.println(this.bucketname);
    }

    @PostMapping("/uploadAudioAnswer")
    @ResponseBody
    public Result audio(@RequestBody Chapter chapter) throws IOException {
        List<String> data = chapter.getText();
        String user = data.get(0);
        String testId = data.get(1);
        String base64Audio = data.get(2);
        System.out.println(base64Audio);

        AudioTest test = testService.findById(testId);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByte = decoder.decode(base64Audio.split(",")[1]);
        String filename = user+ "_" +test.getTitle()+ ".wav";
        String filepath = "./src/main/java/temp/"+filename;
        String key = test.getTitle() + "/" + filename;
        String url = "s3://" + bucketname + "/" + key;
        FileOutputStream fos = new FileOutputStream(filepath);
        fos.write(decodedByte);
        fos.close();

        s3client.putObject(
                bucketname,
                key,
                new File(filepath)
        );

        AudioAnswer answer = new AudioAnswer(new ObjectId().toHexString(), user, key);
        test.addAnswer(answer);
        testService.update(test);

        System.out.println(user);
        System.out.println(test.getTitle());
        System.out.println(key);
        return new Result(StatusCode.SUCCESS, "Upload Success", null);
    }

    @GetMapping("/getAudioAnswerList/{id}")
    @ResponseBody
    public Result getAudioAnswerList(@PathVariable String id) {
        AudioTest test = testService.findById(id);
        List<AudioAnswer> answers = test.getAnswers();
        try {

            // Set the presigned URL to expire after one hour.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = Instant.now().toEpochMilli();
            expTimeMillis += 1000 * 60 * 60;
            expiration.setTime(expTimeMillis);

            for (AudioAnswer answer : answers){
                // Generate the presigned URL.
                GeneratePresignedUrlRequest generatePresignedUrlRequest =
                        new GeneratePresignedUrlRequest(this.bucketname, answer.getKey())
                                .withMethod(HttpMethod.GET)
                                .withExpiration(expiration);
                answer.setKey(this.s3client.generatePresignedUrl(generatePresignedUrlRequest).toString());
            }

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
        return new Result(StatusCode.SUCCESS, "Get Audio Successful", answers);
    }

    @GetMapping("/getAudioAnswer/{testId}/{answerId}")
    @ResponseBody
    public Result getAudioAnswer(@PathVariable String testId, @PathVariable String answerId) {
        AudioAnswer targetAnswer = null;
        AudioTest test = testService.findById(testId);
        List<AudioAnswer> answers = test.getAnswers();
        try {

            // Set the presigned URL to expire after one hour.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = Instant.now().toEpochMilli();
            expTimeMillis += 1000 * 60 * 60;
            expiration.setTime(expTimeMillis);

            for (AudioAnswer answer : answers){
                if (Objects.equals(answer.getId(), answerId)){
                    // Generate the presigned URL.
                    GeneratePresignedUrlRequest generatePresignedUrlRequest =
                            new GeneratePresignedUrlRequest(this.bucketname, answer.getKey())
                                    .withMethod(HttpMethod.GET)
                                    .withExpiration(expiration);
                    answer.setKey(this.s3client.generatePresignedUrl(generatePresignedUrlRequest).toString());
                    targetAnswer = answer;
                }
            }

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
        return new Result(StatusCode.SUCCESS, "Get Audio Successful", targetAnswer);
    }
//    @PostMapping("/uploadFile")
//    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
//        return this.amazonClient.uploadFile(file);
//    }
//
//    @DeleteMapping("/deleteFile")
//    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
//        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
//    }
}

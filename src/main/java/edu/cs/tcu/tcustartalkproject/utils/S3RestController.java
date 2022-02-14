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
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.Pinyin;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Scanner;

@RestController
@RequestMapping("/storage/")
public class S3RestController {
//    private AmazonClient amazonClient;
    private String bucketname;
    private AmazonS3 s3client;

    @Autowired
    S3RestController(AmazonClient amazonClient) throws FileNotFoundException {

//        this.amazonClient = amazonClient;

        Scanner sc = new Scanner(new File("src/main/resources/rootkey.csv"));
        sc.useDelimiter("=|\\n");   //sets the delimiter pattern
        String[] accessKey = new String[4];
        for (int i = 0; sc.hasNext() != false; i++){
            accessKey[i] = sc.next();
        }
        sc.close();  //closes the scanner
        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey[1],
                accessKey[3]);
        this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
        List<Bucket> buckets = s3client.listBuckets();
        for (Bucket bucket : buckets) {
            this.bucketname = bucket.getName();
        }
    }

    @PostMapping("/upload")
    @ResponseBody
    public Result audio(@RequestBody Chapter chapter) throws IOException {
        List<String> data = chapter.getText();
        String user = data.get(0);
        String test = data.get(1);
        String base64Audio = data.get(2);
        System.out.println(base64Audio);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByte = decoder.decode(base64Audio.split(",")[1]);
        String filename = user+ "_" +test+ ".wav";
        String filepath = "./src/main/java/temp/"+filename;
        String key = test + "/" + filename;
        String url = "s3://" + bucketname + "/" + key;
        FileOutputStream fos = new FileOutputStream(filepath);
        fos.write(decodedByte);
        fos.close();

        s3client.putObject(
                bucketname,
                key,
                new File(filepath)
        );
        System.out.println(user);
        System.out.println(test);
        System.out.println(key);
        return new Result(StatusCode.SUCCESS, "Upload Success", null);
    }

    @GetMapping("/getAudio")
    @ResponseBody
    public Result getAudio(@RequestBody String key) {
        URL url = null;
        try {

            // Set the presigned URL to expire after one hour.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = Instant.now().toEpochMilli();
            expTimeMillis += 1000 * 60 * 60;
            expiration.setTime(expTimeMillis);

            // Generate the presigned URL.
            System.out.println("Generating pre-signed URL.");
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(this.bucketname, key)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            url = this.s3client.generatePresignedUrl(generatePresignedUrlRequest);

            System.out.println("Pre-Signed URL: " + url.toString());

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
        return new Result(StatusCode.SUCCESS, "Get Audio Successful", url.toString());
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

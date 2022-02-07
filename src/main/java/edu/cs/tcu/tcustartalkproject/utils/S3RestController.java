package edu.cs.tcu.tcustartalkproject.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

//@RestController
//@RequestMapping("/storage/")
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
        System.out.println(url);
        return new Result(StatusCode.SUCCESS, "Upload Success", null);
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

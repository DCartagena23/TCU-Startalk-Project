package edu.cs.tcu.tcustartalkproject;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class S3BucketConnectionTest {
    public static void main(String... args) throws FileNotFoundException {
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
        AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
        List<Bucket> buckets = s3client.listBuckets();
        for(Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }
    }
}

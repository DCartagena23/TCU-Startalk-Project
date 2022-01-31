package edu.cs.tcu.tcustartalkproject;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TcuStartalkProjectApplication {
    public static void main(String[] args) {
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAUYJLXQWAQFZI4FOI",
                "B88uanzg7BqPFRAr902lbR7GDJAR3TnVyzWmP542"
        );
        AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
        SpringApplication.run(TcuStartalkProjectApplication.class, args);
    }

}

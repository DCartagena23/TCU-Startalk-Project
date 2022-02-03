package edu.cs.tcu.tcustartalkproject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TcuStartalkProjectApplication {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAUYJLXQWAUKS6IVF6",
                "F17P4YPBBxs02cEG6MtW3T0aptgQrkMmiTKCs"
        );
        AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
        SpringApplication.run(TcuStartalkProjectApplication.class, args);
    }
}

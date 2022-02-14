package edu.cs.tcu.tcustartalkproject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
public class TcuStartalkProjectApplication{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/main/resources/rootkey.csv"));
        sc.useDelimiter("=|\\n");   //sets the delimiter pattern
        String[] accessKey = new String[4];
        for (int i = 0; sc.hasNext() != false; i++){
            accessKey[i] = sc.next();
        }
        sc.close();  //closes the scanner
        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey[1],
                accessKey[3]
        );
        AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
        SpringApplication.run(TcuStartalkProjectApplication.class, args);
    }
}

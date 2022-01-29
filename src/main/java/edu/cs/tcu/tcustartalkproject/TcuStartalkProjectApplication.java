package edu.cs.tcu.tcustartalkproject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TcuStartalkProjectApplication {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        SpringApplication.run(TcuStartalkProjectApplication.class, args);
    }

}

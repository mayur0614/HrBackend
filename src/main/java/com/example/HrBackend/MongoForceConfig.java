package com.example.HrBackend;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoForceConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                "mongodb+srv://root:root@springbootcluster.7gylgox.mongodb.net/?appName=SpringBootCluster"
                //"mongodb+srv://root:root@springbootcluster.7gylgox.mongodb.net/test?retryWrites=true&w=majority"
        );
    }
}

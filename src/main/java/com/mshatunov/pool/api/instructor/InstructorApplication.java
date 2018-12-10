package com.mshatunov.pool.api.instructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class InstructorApplication {
    public static void main(String[] args) {
        SpringApplication.run(InstructorApplication.class, args);
    }
}

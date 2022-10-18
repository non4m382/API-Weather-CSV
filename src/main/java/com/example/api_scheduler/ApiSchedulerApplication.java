package com.example.api_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSchedulerApplication.class, args);
    }

}

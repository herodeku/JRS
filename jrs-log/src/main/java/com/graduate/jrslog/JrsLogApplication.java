package com.graduate.jrslog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JrsLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(JrsLogApplication.class, args);
    }

}

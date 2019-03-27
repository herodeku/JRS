package com.graduate.jrsmain;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class JrsMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(JrsMainApplication.class, args);
    }

}

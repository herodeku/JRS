package com.graduate.jrses;

import com.graduate.jrses.bean.Judgment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@SpringBootApplication
public class JrsEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JrsEsApplication.class, args);
    }

}

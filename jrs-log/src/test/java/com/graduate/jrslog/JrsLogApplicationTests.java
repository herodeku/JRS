package com.graduate.jrslog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JrsLogApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
        ArrayList<String> list = new ArrayList<>();
        list.add("鹿晗");
        list.add("蔡徐坤");
        list.add("无敌");
        list.add("2015");
        rabbitTemplate.convertAndSend("Exchange.populace","Queue.populace",list);
    }

}

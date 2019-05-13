package com.graduate.jrslog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JrsLogApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void contextLoads() {
        ArrayList<String> list = new ArrayList<>();
        list.add("鹿晗");
        list.add("蔡徐坤");
        list.add("无敌");
        list.add("2015");
        rabbitTemplate.convertAndSend("rsyslog","queue.syslog",list);
    }

    @Test
    public void syslogInfo(){
        logger.info("哈哈哈");
    }

    @Test
    public void redisTest(){
        Object o = redisTemplate.opsForValue().get("root");
        System.out.println(o);
    }
}

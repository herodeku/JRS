package com.graduate.jrsmain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JrsMainApplicationTests {

    @Test
    public void contextLoads() {
        String s = "山西省运城市人民法院";
        System.out.println(s.contains("中级人民法院"));
    }
}

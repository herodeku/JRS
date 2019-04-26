package com.graduate.jrsmain.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(applicationContext!=null){
            SpringUtil.applicationContext = applicationContext;
        }
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
}

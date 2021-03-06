package com.pazz.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: 彭坚
 * @create: 2019/1/24 11:09
 * @description: ApplicationContextAware中只有一个setApplicationContext方法。
 * 实现了ApplicationContextAware接口的类，可以在该Bean被加载的过程中获取Spring的应用上下文ApplicationContext，
 * 通过ApplicationContext可以获取Spring容器内的很多信息
 */
//@Component
public class ApplicationContextAwareTest implements ApplicationContextAware, InitializingBean {

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            System.out.println("setApplicationContextAware(): ==>> " + applicationContext.getDisplayName());
        }catch(Exception e){
            e.fillInStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}

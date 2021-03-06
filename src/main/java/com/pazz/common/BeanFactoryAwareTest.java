package com.pazz.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: 彭坚
 * @create: 2019/1/24 11:18
 * @description: BeanFactoryAware接口中只有一个setBeanFactory方法。
 * 实现了BeanFactoryAware接口的类，可以在该Bean被加载的过程中获取加载该Bean的BeanFactory，同
 * 时也可以获取这个BeanFactory中加载的其它Bean
 */
//@Component
public class BeanFactoryAwareTest implements BeanFactoryAware, InitializingBean {

    private BeanFactory beanFactory;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(beanFactory.getBean(FactoryBeanTest.class).getObject());
    }

}

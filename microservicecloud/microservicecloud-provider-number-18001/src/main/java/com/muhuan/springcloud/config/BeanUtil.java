package com.muhuan.springcloud.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName BeanConfiguration
 * @Description TODO 动态获取ioc容器组件
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/30
 * @Version 1.0.0
 **/
@Component
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext springContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtil.springContext = applicationContext;
    }

    /**
     * 通过类型获取实例
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls){
        return springContext.getBean(cls);
    }

    public static Object getBean(String beanName){
        return springContext.getBean(beanName);
    }
}

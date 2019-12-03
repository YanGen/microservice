package com.atguigu.springcloud.config;

import com.atguigu.springcloud.aop.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName LoginInterceptorConfig
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/15
 * @Version 1.0.0
 **/


@Configuration
public class LoginInterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
    }
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
}

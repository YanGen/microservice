package com.muhuan.springcloud.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisConfig
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/8
 * @Version 1.0.0
 **/
//Spring boot方式
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
public class MybatisConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

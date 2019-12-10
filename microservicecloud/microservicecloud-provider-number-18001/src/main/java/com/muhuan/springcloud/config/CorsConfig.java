package com.muhuan.springcloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName CorsConfig
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/3
 * @Version 1.0.0
 **/
@Configuration
public class CorsConfig {
    @Value("${cors.addAllowedOrigin}")
    private String addAllowedOrigin;
    @Value("${cors.addAllowedHeader}")
    private String addAllowedHeader;
    @Value("${cors.addAllowedMethod}")
    private String addAllowedMethod;

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(addAllowedOrigin); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader(addAllowedHeader); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod(addAllowedMethod); // 3 设置访问源请求方法
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
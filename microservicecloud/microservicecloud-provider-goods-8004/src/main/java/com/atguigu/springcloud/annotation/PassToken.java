package com.atguigu.springcloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName PassToken
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/15
 * @Version 1.0.0
 **/


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface PassToken {
    boolean required() default true;
}

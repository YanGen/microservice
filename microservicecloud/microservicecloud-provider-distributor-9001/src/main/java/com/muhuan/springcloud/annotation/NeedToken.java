package com.muhuan.springcloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName NeedToken
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/11/13
 * @Version 1.0.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedToken {
    boolean required() default true;
}

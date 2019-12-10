package com.atguigu.springcloud.entities.number.Annotation;

import java.lang.annotation.*;

/**
 * @ClassName ContentValue
 * @Description TODO
 * @Author lin <j.db@uniteddata.com>
 * @Date 2019/10/30 17:48
 * @Version 1.0.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldAnnotation {
     String stringValue() default "";

     int integerValue() default 0;

     long longValue() default 0;

}

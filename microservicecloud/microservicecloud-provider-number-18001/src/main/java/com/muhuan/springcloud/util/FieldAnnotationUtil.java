package com.muhuan.springcloud.util;

import com.muhuan.common.entity.Annotation.FieldAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName FieldAnnotationUtil
 * @Description TODO
 * @Author lin <j.db@uniteddata.com>
 * @Date 2019/11/1 11:26
 * @Version 1.0.0
 **/
public class FieldAnnotationUtil {
    public static <T> T create(T t)  {

        Class t1=t.getClass();

        FieldAnnotation contentValue=(FieldAnnotation)t1.getAnnotation(FieldAnnotation.class);
        //获取所有属性
        Field[] fields = t1.getDeclaredFields();
        for (Field field:fields) {
            try {
                field.setAccessible(true);//true 允许反射时访问私有变量
                Class<?> type = field.getType();// 属性类型
                //获取属性名称
                String name =field.getName();
                //将属性的首字符大写，方便构造get，set方法
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                //调用get方法 判断是否为空
                Method method=t1.getMethod("get"+name);
                //value表示属性值
                Object value=method.invoke(t);
                //obj表示要覆盖的值
                Object obj=value;

                //如果值为空 则调用统一注解
                if(type == Integer.class&&value==null){
                    obj=contentValue.integerValue();
                }
                if(type==String.class&&value==null){
                    obj=contentValue.stringValue();
                }
                if(type==Long.class&&value==null){
                    obj=contentValue.longValue();
                }
                field.set(t,obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}

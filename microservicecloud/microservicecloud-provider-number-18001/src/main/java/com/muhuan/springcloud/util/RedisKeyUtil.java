package com.muhuan.springcloud.util;

/**
 * @ClassName RedisKeyUtil
 * @Description TODO
 * @Author lin <j.db@uniteddata.com>
 * @Date 2019/10/28 10:33
 * @Version 1.0.0
 **/
public class RedisKeyUtil {
    private static final String PROJECT_CONTENT_PREFIX="project-content:";
    private static final String PROJECT_PREFIX="project:";

    public static String projectKey(){
        return  PROJECT_PREFIX;
    }

    public static String projectContentKey(){
        return PROJECT_CONTENT_PREFIX;
    }
}

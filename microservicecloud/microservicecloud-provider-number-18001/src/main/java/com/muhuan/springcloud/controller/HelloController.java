package com.muhuan.springcloud.controller;

import com.muhuan.api.bean.ajax.ResponseResult;
import com.muhuan.api.util.ResultGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO test
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/21
 * @Version 1.0.0
 **/
@RestController
public class HelloController {
    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    @GetMapping("/hello")
    public ResponseResult hello(){
        return ResultGeneratorUtil.getResultSuccessWithData("hello");
    }
}

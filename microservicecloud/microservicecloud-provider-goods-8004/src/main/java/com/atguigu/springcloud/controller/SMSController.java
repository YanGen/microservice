package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class SMSController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @PostMapping("/sendShortMessage")
    public ResponseResult deductStock(){
        Map<String,Object> map = new HashMap<>();
        map.put("number", "13580151262");
        map.put("type", "simple"); // simple 普通文本 yzm 验证码
        map.put("content", "这是一段普通文本"); //type 为 simple 时发的是这一段
        map.put("digit", 4);// 如果是验证码要指定位数
        rabbitTemplate.convertAndSend("messageDirectExchange","messageQueue",map);
        return ResultGeneratorUtil.getResultSuccess();
    }

}

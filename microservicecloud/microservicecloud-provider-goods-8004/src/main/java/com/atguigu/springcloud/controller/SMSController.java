package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.ajax.ResponseResult;
import com.atguigu.springcloud.util.ResultGeneratorUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class SMSController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @PostMapping("/sendShortMessage")
    public ResponseResult deductStock(){
        rabbitTemplate.convertAndSend("messageDirectExchange","messageQueue","13580151262");
        return ResultGeneratorUtil.getResultSuccess();
    }

}

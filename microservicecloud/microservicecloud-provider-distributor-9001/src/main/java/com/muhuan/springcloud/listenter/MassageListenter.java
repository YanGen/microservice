package com.muhuan.springcloud.listenter;

import com.muhuan.springcloud.service.RedisService;
import com.muhuan.springcloud.util.SMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName MassageListenter
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/17
 * @Version 1.0.0
 **/
@Component
public class MassageListenter {
    @Autowired
    private RedisService redisService;

    private static final Logger Log = LoggerFactory.getLogger(GoodListenter.class);


    @RabbitListener(queues = "messageQueue")
    public void SMSV() {
        String yzm = SMSUtil.getYZM(4);
        redisService.set("test",yzm,60);
        try {
            SMSUtil.sendMessage("13580151262",yzm);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}

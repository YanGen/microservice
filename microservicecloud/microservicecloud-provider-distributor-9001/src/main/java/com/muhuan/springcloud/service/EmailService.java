package com.muhuan.springcloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @ClassName EmailService
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/12/19
 * @Version 1.0.0
 **/
@Service
public class EmailService {
    private static final Logger Log = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送简单文本文件
     */

    public void sendSimpleEmail(String from,String to,String subject,String text){
        try {
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);


            mailSender.send(message);
            Log.info("邮箱发送成功");

        }catch (Exception e){
            Log.info("发送简单文本异常");
        }
    }

    /**
     * 发送html文本
     * @param
     */
    @Async
    public void sendHTMLMail(String from,String to,String subject,String text){
        try {
            MimeMessage message=mailSender.createMimeMessage();
            MimeMessageHelper messageHelper=new MimeMessageHelper(message,true,"utf-8");
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text,true);

            mailSender.send(message);
            Log.info("邮箱发送成功");

        }catch (Exception e){
            Log.info("发送HTML文本异常");
        }
    }
}

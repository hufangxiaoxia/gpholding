package com.ilinli.neighbourhoodhelpwebsite.service.impl;

import com.ilinli.neighbourhoodhelpwebsite.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/***
 * @author : 马晓光
 * @date   : 2019/7/27
 * @description :
 **/
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendMail() {
       // MimeMessage message = null;
        SimpleMailMessage message = new SimpleMailMessage();
       // try {
          /*  message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("mark.maxiaoguang@aliyun.com");
            helper.setSubject("标题：发送Html内容");

            StringBuffer sb = new StringBuffer();
            sb.append("<h1>大标题-h1</h1>")
                    .append("<p style='color:#F00'>红色字</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            helper.setText(sb.toString(), true);

            javaMailSender.send(message);*/

            message.setTo("80692072@qq.com");
            message.setSubject("test");
            message.setText("aaaaaaaaaaaaaatest");
            message.setFrom(from);
            javaMailSender.send(message);

       /* } catch (MessagingException e) {
            e.printStackTrace();
        }*/
    }
}

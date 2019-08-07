package com.gpholding.service.impl;

import com.gpholding.constants.GpholdingStatic;
import com.gpholding.dao.UserMapper;
import com.gpholding.service.EmailService;
import com.gpholding.domain.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Properties;


/***
 * @author : 马晓光
 * @date   : 2019/7/27
 * @description :
 **/
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String FROM;
    @Value("${spring.mail.password}")
    private String PASSWORD;

    @Value("${spring.mail.host}")
    private String HOST;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult sendMail(String toMail) {
        Long  current = System.currentTimeMillis();
        String tmp =current.toString();
        String code =tmp.substring(tmp.length()-4,tmp.length());
        //int  intValue =current.intValue();
        //Double code =  (Math.random()*9+1)*1000  ;
        if(StringUtils.isEmpty(toMail)){
            return ResponseResult.build(GpholdingStatic.FAIL_CODE, GpholdingStatic.OBJECT_IS_NULL,null);
        }
        //更新到用户表里
        int count = userMapper.setEmailVerifyCode(toMail,Integer.valueOf(code));
        if (count!=1){
            return ResponseResult.fail("数据库里查无此此人信息，请核实。");
        }
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.smtp.host", HOST);
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.timeout","1000");
        Session session = Session.getInstance(props, auth);

        MimeMessage message  = new MimeMessage(session);;
        try {
            //防止成为垃圾邮件，披上outlook的马甲
            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toMail));
            message.setSubject("verify code");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Your code is :9527");
            Multipart multipart=new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setSentDate(Calendar.getInstance().getTime());
            message.setContent(multipart, "text/html;charset=utf-8");
            Transport.send(message);
        } catch (Exception e) {
            log.error("邮件发送失败", e.getMessage());
            return ResponseResult.fail(null);
        }

        return ResponseResult.success(null);
    }
}

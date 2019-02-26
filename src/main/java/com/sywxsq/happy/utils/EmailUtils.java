package com.sywxsq.happy.utils;

import com.sywxsq.happy.pojo.SywxsqException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮箱工具类
 * @author luokangtao
 * @create 2019-02-26 14:31
 */
@Component
public class EmailUtils {

    private static String Email_163_SMTPHOST;//服务器地址:POP3服务器

    private static String Email_163_FROM;//发件人邮箱地址

    private static String Email_163_NAME;//发件人用户名

    private static String Email_163_PWD;//163邮箱的授权码

    //Spring支持set方法注入，可以利用非静态的setter方法注入静态常量。注意set方法不能有static
    //直接使用 @Value 为静态变量赋值是不行的，可以使用 set 方法：注意，需要使用 @Component 注解。
    @Value("${Email_163_SMTPHOST}")
    public  void setEmail_163_SMTPHOST(String email_163_SMTPHOST) {
        Email_163_SMTPHOST = email_163_SMTPHOST;
    }

    @Value("${Email_163_FROM}")
    public  void setEmail_163_FROM(String email_163_FROM) {
        Email_163_FROM = email_163_FROM;
    }

    @Value("${Email_163_NAME}")
    public  void setEmail_163_NAME(String email_163_NAME) {
        Email_163_NAME = email_163_NAME;
    }

    @Value("${Email_163_PWD}")
    public  void setEmail_163_PWD(String email_163_PWD) {
        Email_163_PWD = email_163_PWD;
    }

    /**
     * 发送邮箱
     * @param subject 主题
     * @param content 内容(可以html标签)
     * @param RecipientsEmail 收件人邮箱地址
     */
    public static void SendEmail(String subject,String content,String RecipientsEmail){

        //设置一个属性
        Properties properties=new Properties();
        //设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        properties.put("mail.smtp.host",Email_163_SMTPHOST);
        //需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        properties.put("mail.smtp.auth","true");

        //用properties对象构建一个session
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication(Email_163_NAME,Email_163_PWD);
            }
        });
        //开启DEBUG模式,在控制台中或日志中有日志信息显示
        session.setDebug(true);

        try {
            //用session为参数定义 MimeMessage 消息对象
            MimeMessage message = new MimeMessage(session);
            //设置发件人邮箱地址-From: 头部头字段
            message.setFrom(new InternetAddress(Email_163_FROM));
            //设置收件人邮箱地址-To: 头部头字段 (也可以批量发邮件,用addRecipients方法)
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(RecipientsEmail));
            //设置在发送给收信人之前给自己（发送方）抄送一份，不然会被当成垃圾邮件，报554错
            message.addRecipient(Message.RecipientType.CC,new InternetAddress(Email_163_FROM));
            //设置邮件标题
            message.setSubject(subject);
            // 发送 HTML 消息, 可以插入html标签
            message.setContent(content,"text/html;charset=UTF-8");
            //发送信息
            Transport.send(message);
            //控制台打印信息
            System.out.println("发送信息到:"+RecipientsEmail+",内容是:"+content);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw  new SywxsqException("系统繁忙,邮箱发送失败,请稍后重试...");
        }


    }

}

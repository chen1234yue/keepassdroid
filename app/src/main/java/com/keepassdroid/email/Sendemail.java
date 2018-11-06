package com.keepassdroid.email;

import android.widget.Toast;

import com.android.keepass.FindpasswordActivity;
import com.android.keepass.R;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sendemail implements Runnable {
    byte[] icode;
    String to;
    public Sendemail(byte[] code,String to){
        super();
        this.to = to;
        icode = code;
    }

    @Override
    public void run() {
        if(to!=null&&isEmail(to))sendEmail1();
    }
    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }
    public  void sendEmail1()
    {
        String sendUserName = "chen1234yue@163.com";
        String sendPassword = "chen970725";
       // String to = "chen1234yue@126.com";
        String from = "chen1234yue@163.com";
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");//服务器需要认证
        properties.setProperty("mail.transport.protocol", "smtp");//声明发送邮件使用的端口

        Session session = Session.getInstance(properties);
        session.setDebug(true);//同意在当前线程的控制台打印与服务器对话信息
        try{
        Message message = new MimeMessage(session);//构建发送的信息
            message.setSubject("验证码信息");
        message.setText("你好，验证码为"+(char)icode[0]+(char)icode[1]+(char)icode[2]+(char)icode[3]);//信息内容
        message.setFrom(new InternetAddress(from));//发件人

        Transport transport = session.getTransport();
        transport.connect("smtp.163.com", 25, sendUserName, sendPassword);//连接发件人使用发件的服务器
        transport.sendMessage(message, new Address[]{new InternetAddress(to)});//接受邮件
        transport.close();
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public static void sendEmail()
    {
        String sendUserName = "chen1234yue@163.com";
        String sendPassword = "chen13401798418";
        // 收件人电子邮箱
        //  String to = App.getDB().pm.email;
        String to = "chen1234yue@126.com";
        // 发件人电子邮箱
        String from = "chen1234yue@163.com";

        // 指定发送邮件的主机为 localhost
        String host = "localhost";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties);

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("This is the Password!");

            // 设置消息体
            message.setText("This is your password : ");//+App.getDB().pm.masterKey

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

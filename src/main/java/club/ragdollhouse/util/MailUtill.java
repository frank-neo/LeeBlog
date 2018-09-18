package club.ragdollhouse.util;

import org.apache.log4j.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * 写一个邮件发送工具
 */
public class MailUtill {


    Logger logger = Logger.getLogger(MailUtill.class);

    private String email ;
    private String code ;

    //显示的有参数构造函数
    public MailUtill(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public void sendRegisterEmail() {

        String from = "franklee8399@163.com";// 发件人电子邮箱
        String host = "smtp.163.com";// 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)
        Properties properties = System.getProperties();// 获取系统属性
        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");// 打开认证


        try {
            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("franklee8399@163.com", "lsx7797LSX"); // 发件人邮箱账号、授权码

                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人

            message.setFrom(new InternetAddress(from));

            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("账号激活");
            // 2.4设置邮件内容
            String content = "<html><head></head><body><h1>欢迎加入ragdollhouse.club,这是一封激活邮件,激活请点击以下链接(此链接仅限当天有效)</h1><h3><a href='http://ragdollhouse.club/RegisterDemo/ActiveServlet?code="
                    + code + "'>http://ragdollhouse.club/activateRegister?code=" + code
                    + "</href></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
            logger.info("邮件成功发送");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

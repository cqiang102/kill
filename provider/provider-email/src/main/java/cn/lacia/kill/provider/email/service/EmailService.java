package cn.lacia.kill.provider.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @author 你是电脑
 * @create 2020/1/8 - 16:08
 */
@Service
public class EmailService {
    @Resource
    private ConfigurableApplicationContext applicationContext;
    @Autowired
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;
    @Async
    public void receive(String body,String to) {
        Context context = new Context();
        //往 模板中塞数据
        context.setVariable("test",body) ;
        String emailTemplate = templateEngine.process("info", context);
        sendTemplateEmail("支付宝到账100万元", emailTemplate, to);
    }
    /**
     * 发送普通邮件
     * @param subject
     * @param body
     * @param to
     */
    @Async
    public void sendEmail(String subject, String body, String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(applicationContext.getEnvironment().getProperty("spring.mail.username"));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
    /**
     * 发送 HTML 模板邮件
     * @param subject
     * @param body
     * @param to
     */
    @Async
    public void sendTemplateEmail(String subject, String body, String to) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String property = applicationContext.getEnvironment().getProperty("spring.mail.username");
            assert property != null;
            helper.setFrom(property);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (Exception e) {
        }
    }
}

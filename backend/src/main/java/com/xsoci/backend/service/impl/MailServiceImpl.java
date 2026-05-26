package com.xsoci.backend.service.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import com.xsoci.backend.service.MailService;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    private void mailProcess(String to, String username, String subject, String expireAt, String path) {
        try {
            Context context = new Context();
            context.setVariable("username", username);

            String htmlContent = templateEngine.process("mail/" + path, context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.setFrom("buitantrung01@gmail.com");

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Send mail failed", e);
        }
    }

    @Override
    public void sendVerifyMail(String to, String username, String expireAt){
        this.mailProcess(to, username, "Verify your account", username, expireAt);
    }

    @Override
    public void sendWelcomeMail(String to, String username) {
        this.mailProcess(to, username, "Welcome Brooo!", username, null);
    }

    @Override
    public void sendForgotPasswordMail(String to, String username){
        this.mailProcess(to, username, "Reset Password", username, null);
    }
}

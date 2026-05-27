package com.xsoci.backend.service.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import com.xsoci.backend.constant.FieldConstants;
import com.xsoci.backend.constant.HttpConstants;
import com.xsoci.backend.constant.PathConstants;
import com.xsoci.backend.constant.UrlConstants;
import com.xsoci.backend.constant.VariableConstants;
import com.xsoci.backend.exception.CustomException;
import com.xsoci.backend.service.MailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${app.mail.from}")
    private String from;

    /**
     * Main method to process sends mail
     * 
     * @param to
     * @param username
     * @param subject
     * @param expireAt
     * @param path
     * @param token
     */
    private void mailProcess(
            String to,
            String username,
            String subject,
            String expireAt,
            String path,
            String token) {
        try {
            Context context = new Context();
            context.setVariable("username", username);

            if (token != null) {
                switch (path) {
                    case PathConstants.VERIFY_ACCOUNT_PATH:
                        String verifyUrl = baseUrl + UrlConstants.VERIFY_URL + token;
                        context.setVariable("verifyUrl", verifyUrl);
                        break;

                    case PathConstants.RESET_PASSWORD_PATH:
                        String resetUrl = baseUrl + UrlConstants.RESET_URL + token;
                        context.setVariable("resetUrl", resetUrl);
                        break;

                    default:
                        break;
                }
            }

            if (expireAt != null) {
                context.setVariable("expireAt", expireAt);
            }

            String htmlContent = templateEngine.process(PathConstants.MAIL_PREFIX_PATH + path, context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, VariableConstants.CHARSET_UTF_8);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.setFrom(from);

            mailSender.send(message);
        } catch (Exception e) {
            throw new CustomException(
                HttpConstants.HTTP_500,
                e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void sendVerifyMail(String to, String username, LocalDateTime expireAt, String token) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        String formattedExpireAt = expireAt.format(dateFormatter);
        this.mailProcess(to, username, FieldConstants.VEFIFY_ACCOUNT, formattedExpireAt, PathConstants.VERIFY_ACCOUNT_PATH, token);
    }

    @Override
    public void sendWelcomeMail(String to, String username) {
        String title = FieldConstants.WELCOME + " " + username;
        this.mailProcess(to, username, title, null, PathConstants.WELCOME_PATH, null);
    }

    @Override
    public void sendForgotPasswordMail(String to, String username, String token) {
        this.mailProcess(to, username, FieldConstants.RESET_PASSWORD, null, PathConstants.RESET_PASSWORD_PATH, token);
    }
}

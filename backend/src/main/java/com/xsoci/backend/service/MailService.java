package com.xsoci.backend.service;

import java.time.LocalDateTime;

public interface MailService {
    void sendVerifyMail(String to, String username, LocalDateTime expireAt, String token);

    void sendWelcomeMail(String to, String username);

    void sendForgotPasswordMail(String to, String username);
}

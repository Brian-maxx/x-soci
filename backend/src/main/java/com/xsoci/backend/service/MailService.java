package com.xsoci.backend.service;

public interface MailService {
    void sendVerifyMail(String to, String username, String expireAt);
    void sendWelcomeMail(String to, String username);
    void sendForgotPasswordMail(String to, String username);
}

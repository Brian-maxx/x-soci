package com.xsoci.backend.service;

import com.xsoci.backend.dto.request.LoginRequest;
import com.xsoci.backend.dto.request.RegisterRequest;
import com.xsoci.backend.dto.response.AuthResponse;
import com.xsoci.backend.dto.response.MessageResponse;
import com.xsoci.backend.dto.response.RegisterResponse;
import com.xsoci.backend.dto.request.ChangePasswordRequest;
import com.xsoci.backend.dto.request.ForgotPasswordRequest;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
    MessageResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
    MessageResponse changePassword(ChangePasswordRequest changPasswordRequest);
    MessageResponse refreshToken(String token);
    MessageResponse logout(String refreshToken);
    MessageResponse verifyEmail(String param);
}
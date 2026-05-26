package com.xsoci.backend.service;

import com.xsoci.backend.dto.request.LoginRequest;
import com.xsoci.backend.dto.request.RegisterRequest;
import com.xsoci.backend.dto.response.LoginResponse;
import com.xsoci.backend.dto.response.MessageResponse;
import com.xsoci.backend.dto.response.RegisterResponse;
import com.xsoci.backend.dto.request.ChangePasswordRequest;
import com.xsoci.backend.dto.request.ForgotPasswordRequest;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    MessageResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
    MessageResponse changePassword(ChangePasswordRequest changPasswordRequest);
}
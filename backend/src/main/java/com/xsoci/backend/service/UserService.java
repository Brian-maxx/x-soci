package com.xsoci.backend.service;

import com.xsoci.backend.dto.request.LoginRequest;
import com.xsoci.backend.dto.request.RegisterRequest;
import com.xsoci.backend.dto.response.UserResponse;
import com.xsoci.backend.dto.request.ChangePasswordRequest;
import com.xsoci.backend.dto.request.ForgotPasswordRequest;

public interface UserService {
    UserResponse register(RegisterRequest registerRequest);
    UserResponse login(LoginRequest loginRequest);
    UserResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
    UserResponse changePassword(ChangePasswordRequest changPasswordRequest);
}
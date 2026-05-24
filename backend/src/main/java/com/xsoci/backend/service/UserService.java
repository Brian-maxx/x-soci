package com.xsoci.backend.service;

import com.xsoci.backend.dto.request.LoginRequest;
import com.xsoci.backend.dto.request.RegisterRequest;
import com.xsoci.backend.dto.response.UserResponse;

public interface UserService {
    UserResponse register(RegisterRequest registerRequest);
    UserResponse login(LoginRequest loginRequest);
}
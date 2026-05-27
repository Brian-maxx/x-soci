package com.xsoci.backend.service;
import com.xsoci.backend.entity.User;
import com.xsoci.backend.dto.response.AuthResponse;

public interface JwtService {
    AuthResponse buildAuthResponse(String accessToken, String refreshToken, User user);
    AuthResponse createToken(User user);
    AuthResponse rotateRefreshToken(String refreshToken);
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    long getAccessTokenExpiration(); 
}
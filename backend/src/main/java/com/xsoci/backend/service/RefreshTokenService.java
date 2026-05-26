package com.xsoci.backend.service;
import com.xsoci.backend.entity.RefreshToken;
import com.xsoci.backend.entity.User;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user, String token);
    RefreshToken revokeRefreshToken(String refreshToken);
}
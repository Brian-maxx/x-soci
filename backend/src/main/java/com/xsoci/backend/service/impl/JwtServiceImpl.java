package com.xsoci.backend.service.impl;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.xsoci.backend.mapper.UserMapper;
import com.xsoci.backend.security.JwtAuthentication;
import com.xsoci.backend.service.JwtService;
import com.xsoci.backend.service.RefreshTokenService;
import com.xsoci.backend.constant.VariableConstants;
import com.xsoci.backend.entity.User;
import com.xsoci.backend.dto.response.AuthResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {   
    private final RefreshTokenService refreshTokenService;
    private final JwtAuthentication jwtAuthentication;

    /**
     * Method build auth response
     * 
     * @param accessToken
     * @param refreshToken
     * @param user
     * @return
     */
    public AuthResponse buildAuthResponse(String accessToken, String refreshToken, User user) {
        return AuthResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .tokenType(VariableConstants.JWT_TOKEN_TYPE)
            .expiresIn(jwtAuthentication.getAccessTokenExpiration() / 1000)
            .user(UserMapper.toUserResponse(user))
            .build();
    }

    /**
     * Process create token to access the system
     */
    public AuthResponse createToken(User user) {
        String accessToken = jwtAuthentication.generateAccessToken(user);
        String refreshToken = jwtAuthentication.generateRefreshToken(user);

        refreshTokenService.createRefreshToken(user, refreshToken);

        return this.buildAuthResponse(accessToken, refreshToken, user);
    }

    /**
     * Process get new refresh token to access the system after expired
     */
    @Transactional
    public AuthResponse rotateRefreshToken(String refreshToken) {
        User user = refreshTokenService.revokeRefreshToken(refreshToken).getUser();

        String newAccessToken = this.generateAccessToken(user);
        String newRefreshToken = this.generateRefreshToken(user);

        refreshTokenService.createRefreshToken(user, newRefreshToken);

        return this.buildAuthResponse(newAccessToken, newRefreshToken, user);
    }

    /**
     * Call Jwt Security to generate access token
     * 
     * @param user
     */
    public String generateAccessToken(User user) {
        return jwtAuthentication.generateAccessToken(user);
    }

    /**
     * Call Jwt Security to generate refresh token
     * 
     * @param user
     */
    public String generateRefreshToken(User user) {
        return jwtAuthentication.generateRefreshToken(user);
    }

    /**
     * Call Jwt Security to get access token expired
     */
    public long getAccessTokenExpiration() {
        return jwtAuthentication.getAccessTokenExpiration();
    }
}
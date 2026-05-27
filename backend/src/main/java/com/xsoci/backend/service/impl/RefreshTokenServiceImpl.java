package com.xsoci.backend.service.impl;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.xsoci.backend.exception.CustomException;
import com.xsoci.backend.service.RefreshTokenService;
import com.xsoci.backend.repository.RefreshTokenRepository;
import com.xsoci.backend.constant.FieldConstants;
import com.xsoci.backend.constant.HttpConstants;
import com.xsoci.backend.entity.User;
import com.xsoci.backend.entity.RefreshToken;
import com.xsoci.backend.util.ValidationUtil;
import com.xsoci.backend.util.MessageUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {   
    private final RefreshTokenRepository refreshTokenRepository;
    private final ValidationUtil validationUtil;
    private final MessageUtil messageUtil;

    public RefreshToken getUserByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken)
            .orElse(null);
    }

    @Transactional
    public RefreshToken createRefreshToken(User user, String token){
        return refreshTokenRepository.save(
            Objects.requireNonNull(RefreshToken.builder()
                .token(token)
                .user(user)
                .expiredAt(LocalDateTime.now().plusDays(7))
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build())
        );
    }

    public RefreshToken getValidRefreshToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
            .orElseThrow(() -> 
                new CustomException(
                    HttpConstants.HTTP_400, 
                    messageUtil.invalid(refreshToken),
                    HttpStatus.BAD_REQUEST
                )
            );

        if(token.isRevoked() || token.getExpiredAt().isBefore(LocalDateTime.now())) {
            validationUtil.throwInvalid(FieldConstants.REFRESH_TOKEN);
        }

        return token;
    }

    @Transactional
    public RefreshToken revokeRefreshToken(String refreshToken) {
        RefreshToken token = this.getValidRefreshToken(refreshToken);
        token.setRevoked(true);
        refreshTokenRepository.save(token);
        return token;
    }
}
package com.xsoci.backend.service;
import com.xsoci.backend.entity.RefreshToken;
import com.xsoci.backend.entity.User;

import lombok.NonNull;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(@NonNull User user,@NonNull String token);
    RefreshToken revokeRefreshToken(String refreshToken);
    RefreshToken getValidRefreshToken(String refreshToken);
}
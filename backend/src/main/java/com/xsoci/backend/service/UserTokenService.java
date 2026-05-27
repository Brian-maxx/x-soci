package com.xsoci.backend.service;

import com.xsoci.backend.entity.UserToken;
import com.xsoci.backend.entity.User;

public interface UserTokenService {
    UserToken createUserToken(User user, String token, String type);

    UserToken getByTokenAndType(String token, String type);

    void deleteUserToken(String token, String type);
}
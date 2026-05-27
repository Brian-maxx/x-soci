package com.xsoci.backend.service.impl;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.xsoci.backend.repository.UserTokenRepository;
import com.xsoci.backend.service.UserTokenService;
import com.xsoci.backend.entity.User;
import com.xsoci.backend.entity.UserToken;
import com.xsoci.backend.util.TimeUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTokenServiceImpl implements UserTokenService {
    private final UserTokenRepository userTokenRepository;

    @Transactional
    @Override
    public UserToken createUserToken(User user, String token, String type) {
        UserToken userToken = UserToken.builder()
                .user(user)
                .token(token)
                .type(type)
                .expiredAt(TimeUtil.expireDateTime())
                .build();

        return userTokenRepository.save(userToken);
    }

    public UserToken getByTokenAndType(String token, String type) {
        return userTokenRepository.findByTokenAndType(token, type)
                .orElse(null);
    }

    /**
     * Delete Token from Database to reduce data
     */
    @Transactional
    @Override
    public void deleteUserToken(String token, String type) {
        userTokenRepository.deleteByTokenAndType(token, type);
    }

}
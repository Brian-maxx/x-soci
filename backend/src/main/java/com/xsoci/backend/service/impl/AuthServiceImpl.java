package com.xsoci.backend.service.impl;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.xsoci.backend.mapper.UserMapper;
import com.xsoci.backend.service.AuthService;
import com.xsoci.backend.service.JwtService;
import com.xsoci.backend.service.RefreshTokenService;
import com.xsoci.backend.service.UserService;
import com.xsoci.backend.service.UserTokenService;
import com.xsoci.backend.constant.FieldConstants;
import com.xsoci.backend.constant.VariableConstants;
import com.xsoci.backend.entity.User;
import com.xsoci.backend.entity.UserToken;
import com.xsoci.backend.dto.request.RegisterRequest;
import com.xsoci.backend.dto.response.AuthResponse;
import com.xsoci.backend.dto.response.MessageResponse;
import com.xsoci.backend.dto.response.RegisterResponse;
import com.xsoci.backend.dto.request.LoginRequest;
import com.xsoci.backend.dto.request.ForgotPasswordRequest;
import com.xsoci.backend.dto.request.ChangePasswordRequest;
import com.xsoci.backend.util.CurrentUserUtil;
import com.xsoci.backend.util.MessageUtil;
import com.xsoci.backend.util.StringRandomGeneratorUtil;
import com.xsoci.backend.util.TimeUtil;
import com.xsoci.backend.util.ResponseUtil;
import com.xsoci.backend.util.ValidationUtil;

import com.xsoci.backend.service.MailService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final JwtService jwtService;
    private final MailService mailService;
    private final UserTokenService userTokenService;
    private final MessageUtil messageUtil;
    private final ValidationUtil validationUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse buildAuthResponse(String accessToken, String refreshToken, User user) {
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(VariableConstants.JWT_TOKEN_TYPE)
                .expiresIn(jwtService.getAccessTokenExpiration() / 1000)
                .user(UserMapper.toUserResponse(user))
                .build();
    }

    public void matchesPassword(User user, String request) {
        boolean passwordMatch = passwordEncoder.matches(request, user.getPassword());

        if (!passwordMatch) {
            validationUtil.throwInvalid(FieldConstants.PASSWORD);
        }
    }

    public void validateUserStatus(User user) {
        boolean isActive = Boolean.TRUE.equals(user.getIsActive());
        boolean isDeleted = user.getDeletedAt() != null;

        if (!isActive) {
            validationUtil.throwInactive(user.getEmail());
        }
        if (isDeleted) {
            validationUtil.throwIsDeleted(user.getEmail());
        }
    }

    @Transactional
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = userService.createUser(registerRequest);
        String verifyToken = StringRandomGeneratorUtil.generate(VariableConstants.MAX_STRING_LENTH);
        userTokenService.createUserToken(user, verifyToken, VariableConstants.TYPE_VERIFY_ACCOUNT);

        mailService.sendVerifyMail(
                user.getEmail(),
                user.getUsername(),
                TimeUtil.generateDateTime(),
                verifyToken);

        return RegisterResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(messageUtil.getMessage(
                        "server.200",
                        new Object[] { FieldConstants.REGISTER }))
                .user(UserMapper.toUserResponse(user))
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {
        User user = userService.getUserByEmail(loginRequest.getEmail());

        String rawPassword = loginRequest.getPassword();
        this.validateUserStatus(user);
        this.matchesPassword(user, rawPassword);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        refreshTokenService.createRefreshToken(user, refreshToken);

        return this.buildAuthResponse(accessToken, refreshToken, user);
    }

    @Transactional
    public MessageResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = userService.getUserByEmail(forgotPasswordRequest.getEmail());
        userService.updatePassword(
                user,
                StringRandomGeneratorUtil.generate(VariableConstants.MIN_PASSWORD_LENGTH));

        return ResponseUtil.success(
                messageUtil.success(FieldConstants.FORGOT_PASSWORD));
    }

    @Transactional
    public MessageResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        String email = CurrentUserUtil.getCurrentUser();
        User user = userService.getUserByEmail(email);
        this.matchesPassword(user, changePasswordRequest.getCurrentPassword());
        userService.updatePassword(user, changePasswordRequest.getNewPassword());

        return ResponseUtil.success(
                messageUtil.success(FieldConstants.CHANGE_PASSWORD));
    }

    public MessageResponse refreshToken(String token) {

        return ResponseUtil.success(
                messageUtil.success(FieldConstants.REFRESH_TOKEN));
    }

    public MessageResponse logout(String refreshToken) {
        refreshTokenService.revokeRefreshToken(refreshToken);

        return ResponseUtil.success(
                messageUtil.success(FieldConstants.REFRESH_TOKEN));
    }

    public MessageResponse verifyEmail(String param) {
        String type = VariableConstants.TYPE_VERIFY_ACCOUNT;
        UserToken userToken = userTokenService.getByTokenAndType(param, type);
        if (userToken != null) {
            User user = userToken.getUser();

            userTokenService.deleteUserToken(param, type);

            userService.activeUser(user.getEmail());

            return ResponseUtil.success(
                    messageUtil.success(FieldConstants.TOKEN));
        }

        return ResponseUtil.fail(
                messageUtil.invalid(FieldConstants.TOKEN));
    }
}
package com.xsoci.backend.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.xsoci.backend.dto.request.RegisterRequest;
import com.xsoci.backend.dto.response.LoginResponse;
import com.xsoci.backend.dto.response.MessageResponse;
import com.xsoci.backend.dto.response.RegisterResponse;
import com.xsoci.backend.entity.User;
import com.xsoci.backend.exception.CustomException;
import com.xsoci.backend.mapper.UserMapper;
import com.xsoci.backend.repository.UserRepository;
import com.xsoci.backend.security.JwtAuthentication;
import com.xsoci.backend.repository.RoleRepository;
import com.xsoci.backend.service.AuthService;
import com.xsoci.backend.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.xsoci.backend.constant.FieldConstants;
import com.xsoci.backend.constant.RoleConstants;
import com.xsoci.backend.constant.VariableConstants;
import com.xsoci.backend.entity.Role;
import com.xsoci.backend.dto.request.LoginRequest;
import com.xsoci.backend.constant.HttpConstants;
import com.xsoci.backend.util.CurrentUserUtil;
import com.xsoci.backend.util.MessageUtil;
import com.xsoci.backend.util.PasswordGeneratorUtil;
import com.xsoci.backend.util.ResponseUtil;
import com.xsoci.backend.dto.request.ForgotPasswordRequest;
import com.xsoci.backend.dto.request.ChangePasswordRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationUtil validationUtil;
    private final MessageUtil messageUtil;
    private final JwtAuthentication jwtAuthentication;

    public void matchesPassword(User user, String request) {
        boolean passwordMatch = 
            passwordEncoder.matches(request, user.getPassword());

        if(!passwordMatch) {
            validationUtil.throwInvalid(FieldConstants.PASSWORD);
        }
    }

    @Transactional
    public void updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> 
                new CustomException(
                    HttpConstants.HTTP_400, 
                    messageUtil.getMessage(
                        "validation.not_found", 
                        new Object[]{FieldConstants.EMAIL}), 
                    HttpStatus.BAD_REQUEST
                )
            );
    }

    @Transactional
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            validationUtil.throwExists(FieldConstants.EMAIL);
        }

        if(userRepository.existsByUsername(registerRequest.getUsername())) {
            validationUtil.throwExists(FieldConstants.USERNAME);
        }

        if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            validationUtil.throwNotMatch(FieldConstants.PASSWORD, FieldConstants.CONFIRM_PASSWORD);
        }

        Role role = roleRepository.findByRoleName(RoleConstants.USER)
            .orElseThrow(() -> 
                new CustomException(
                    HttpConstants.HTTP_400, 
                    messageUtil.getMessage("validation.role.invalid"), 
                    HttpStatus.BAD_REQUEST
                )
            );

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = User.builder()
            .username(registerRequest.getUsername())
            .email(registerRequest.getEmail())
            .password(encodedPassword)
            .fullName(registerRequest.getFullName())
            .phoneNumber(registerRequest.getPhoneNumber())
            .role(role)
            .isActive(VariableConstants.IS_ACTIVE)
            .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
            .statusCode(HttpStatus.OK.value())
            .statusMessage(messageUtil.getMessage(
                "server.200", 
                new Object[]{FieldConstants.REGISTER}    
            ))
            .user(UserMapper.toUserResponse(savedUser))
            .build();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = this.getUser(loginRequest.getEmail());
        
        boolean isActive = Boolean.TRUE.equals(user.getIsActive());
        boolean isDeleted = user.getDeletedAt() != null;
        
        if(!isActive) {
            validationUtil.throwInactive(loginRequest.getEmail());
        } 
        if(isDeleted) {
            validationUtil.throwIsDeleted(loginRequest.getEmail());
        }
        this.matchesPassword(user, loginRequest.getPassword());

        String accessToken = jwtAuthentication.generateAccessToken(user);
        String refreshToken = jwtAuthentication.generateRefreshToken(user);

        return LoginResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .tokenType(VariableConstants.JWT_TOKEN_TYPE)
            .expiresIn(jwtAuthentication.getAccessTokenExpiration() / 1000)
            .user(UserMapper.toUserResponse(user))
            .build();
    }

    @Transactional
    public MessageResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = this.getUser(forgotPasswordRequest.getEmail());
        this.updatePassword(user, PasswordGeneratorUtil.generate(8));

        return ResponseUtil.success(
            messageUtil.success(FieldConstants.FORGOT_PASSWORD)
        );
    }

    @Transactional
    public MessageResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        String email = CurrentUserUtil.getCurrentUser();
        User user = this.getUser(email);
        this.matchesPassword(user, changePasswordRequest.getCurrentPassword());
        this.updatePassword(user, changePasswordRequest.getNewPassword());

        return ResponseUtil.success(
            messageUtil.success(FieldConstants.CHANGE_PASSWORD)
        );
    }
}
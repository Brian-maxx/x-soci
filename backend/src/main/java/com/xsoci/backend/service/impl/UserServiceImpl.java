package com.xsoci.backend.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.xsoci.backend.dto.request.RegisterRequest;
import com.xsoci.backend.dto.response.UserResponse;
import com.xsoci.backend.entity.User;
import com.xsoci.backend.exception.CustomException;
import com.xsoci.backend.mapper.UserMapper;
import com.xsoci.backend.repository.UserRepository;
import com.xsoci.backend.repository.RoleRepository;
import com.xsoci.backend.service.UserService;
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
import com.xsoci.backend.dto.request.ForgotPasswordRequest;
import com.xsoci.backend.dto.request.ChangePasswordRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationUtil validationUtil;
    private final MessageUtil messageUtil;

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
                        "validation.role.not_found", 
                        new Object[]{FieldConstants.EMAIL}), 
                    HttpStatus.BAD_REQUEST
                )
            );
    }

    @Transactional
    @Override
    public UserResponse register(RegisterRequest registerRequest) {
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

        return UserMapper.toUserResponse(savedUser);
    }

    public UserResponse login(LoginRequest loginRequest) {
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

        return UserMapper.toUserResponse(user);
    }

    @Transactional
    public UserResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = this.getUser(forgotPasswordRequest.getEmail());
        this.updatePassword(user, PasswordGeneratorUtil.generate(8));

        return UserMapper.toUserResponse(user);
    }

    @Transactional
    public UserResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        String email = CurrentUserUtil.getCurrentUser();
        User user = this.getUser(email);
        this.matchesPassword(user, changePasswordRequest.getCurrentPassword());
        this.updatePassword(user, changePasswordRequest.getNewPassword());
        return UserMapper.toUserResponse(user);
    }
}
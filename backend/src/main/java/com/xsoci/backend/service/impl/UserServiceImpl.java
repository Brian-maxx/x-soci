package com.xsoci.backend.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.xsoci.backend.entity.User;
import com.xsoci.backend.entity.Role;
import com.xsoci.backend.constant.FieldConstants;
import com.xsoci.backend.constant.HttpConstants;
import com.xsoci.backend.constant.RoleConstants;
import com.xsoci.backend.constant.VariableConstants;
import com.xsoci.backend.dto.request.RegisterRequest;
import com.xsoci.backend.exception.CustomException;
import com.xsoci.backend.repository.UserRepository;
import com.xsoci.backend.repository.RoleRepository;
import com.xsoci.backend.service.UserService;
import com.xsoci.backend.util.MessageUtil;
import com.xsoci.backend.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final MessageUtil messageUtil;
    private final ValidationUtil validationUtil;
    private final PasswordEncoder passwordEncoder;

    /**
     * Get user by email request
     */
    public User getUserByEmail(String email) {
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

    /**
     * Update passsword of user
     * 
     * @param user
     * @param password
     */
    @Transactional
    public void updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    /**
     * Create user to access the system
     */
    @Transactional
    public User createUser(RegisterRequest registerRequest) {
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
            .isActive(VariableConstants.IS_INACTIVE)
            .build();

        return userRepository.save(user);
    }

    /**
     * Turn on Active to access the system
     */
    @Transactional
    public void activeUser(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> 
                new CustomException(
                    HttpConstants.HTTP_400, 
                    messageUtil.getMessage(
                        "validation.not_found",
                        new Object[]{FieldConstants.EMAIL}
                    ), 
                    HttpStatus.BAD_REQUEST
                )
            );

        user.setIsActive(VariableConstants.IS_ACTIVE);
        userRepository.save(user);
    }

}
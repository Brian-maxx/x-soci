package com.xsoci.backend.service.impl;

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
import com.xsoci.backend.entity.Role;
import com.xsoci.backend.dto.request.LoginRequest;
import java.util.Optional;
import com.xsoci.backend.constant.VariableConstants;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationUtil validationUtil;

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
            .orElseThrow(() -> new CustomException("{validation.role.invalid}"));

        User user = User.builder()
            .username(registerRequest.getUsername())
            .email(registerRequest.getEmail())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .fullName(registerRequest.getFullName())
            .phoneNumber(registerRequest.getPhoneNumber())
            .role(role)
            .isActive(true)
            .build();

        User savedUser = userRepository.save(user);

        return UserMapper.toUserResponse(savedUser);
    }

    public UserResponse login(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        
        if(optionalUser.isEmpty()) {
            validationUtil.throwNotFound(loginRequest.getEmail());
        }

        User user = optionalUser.get();

        boolean passwordMatch = 
            passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        boolean isActive =
            user.getIsActive().equals(VariableConstants.IS_ACTIVE);

        boolean isDeleted = user.getDeletedAt() != null;

        if(!passwordMatch) {
            validationUtil.throwInvalid(FieldConstants.PASSWORD);
        }

        if(!isActive) {
            validationUtil.throwInactive(loginRequest.getEmail());
        }

        if(isDeleted) {
            validationUtil.throwIsDeleted(loginRequest.getEmail());
        }

        return UserMapper.toUserResponse(user);
    }
}
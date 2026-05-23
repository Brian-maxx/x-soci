package com.xsoci.backend.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xsoci.backend.dto.request.RegisterRequest;
import com.xsoci.backend.dto.response.UserResponse;
import com.xsoci.backend.entity.User;
import com.xsoci.backend.mapper.UserMapper;
import com.xsoci.backend.repository.UserRepository;
import com.xsoci.backend.repository.RoleRepository;
import com.xsoci.backend.service.UserService;
import com.xsoci.backend.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.xsoci.backend.constant.FieldConstants;
import com.xsoci.backend.constant.RoleConstants;
import com.xsoci.backend.entity.Role;

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

        Role role = roleRepository.findByRoleName("USER")
            .orElseThrow(() -> new RuntimeException("Role not found"));

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
}
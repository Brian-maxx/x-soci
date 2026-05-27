package com.xsoci.backend.service;
import com.xsoci.backend.dto.request.RegisterRequest;
import com.xsoci.backend.entity.User;

public interface UserService {
    User getUserByEmail(String email);
    void updatePassword(User user, String password);
    User createUser(RegisterRequest registerRequest);
    void activeUser(String email);
}
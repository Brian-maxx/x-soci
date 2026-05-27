package com.xsoci.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.xsoci.backend.dto.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import com.xsoci.backend.service.AuthService;

import jakarta.validation.Valid;

import com.xsoci.backend.dto.request.ChangePasswordRequest;
import com.xsoci.backend.dto.request.ForgotPasswordRequest;
import com.xsoci.backend.dto.request.LoginRequest;




@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(authService.forgotPassword(request));
    }

    @GetMapping("/reset")
    public ResponseEntity<?> verifyResetPassword(@RequestParam String token) {
        return ResponseEntity.ok(authService.verifyResetPassword(token));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(authService.changePassword(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody String refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody String refreshToken) {        
        return ResponseEntity.ok(authService.logout(refreshToken));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
         return ResponseEntity.ok(authService.verifyEmail(token));
    }
}

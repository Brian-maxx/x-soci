package com.xsoci.backend.dto.request;

import lombok.Data;
import jakarta.validation.constraints.*;
import com.xsoci.backend.constant.RegexPattern;

@Data
public class RegisterRequest {
    @NotBlank(message = "{auth.username.required}")
    @Size(min = 3, max = 255, message = "{auth.username.size}")
    @Pattern(regexp = RegexPattern.USERNAME, message = "{auth.username.pattern}")
    private String username;

    @NotBlank(message = "{auth.email.required}")
    @Email(message = "{auth.email.invalid}")
    private String email;

    @NotBlank(message = "{auth.password.required}")
    @Size(min = 8, max = 255, message = "{auth.password.size}")
    @Pattern(regexp = RegexPattern.PASSWORD, message = "{auth.password.pattern}")
    private String password;

    @NotBlank(message = "{auth.confirm-password.required}")
    @Size(min = 8, max = 255, message = "{auth.confirm-password.size}")
    @Pattern(regexp = RegexPattern.PASSWORD, message = "{auth.confirm-password.pattern}")
    private String confirmPassword;

    @NotBlank(message = "{auth.full-name.required}")
    @Size(min = 3, max = 255, message = "{auth.full-name.size}")
    private String fullName;

    @NotBlank(message = "{auth.phone.required}")
    @Pattern(regexp = RegexPattern.PHONE_NUMBER, message = "{auth.phone.pattern}")
    private String phoneNumber;
}
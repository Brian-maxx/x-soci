package com.xsoci.backend.dto.request;

import lombok.Data;
import jakarta.validation.constraints.*;
import com.xsoci.backend.constant.RegexPattern;

@Data
public class LoginRequest {
    @NotBlank(message = "{auth.email.required}")
    @Size(min = 3, max = 255, message = "{auth.email.size}")
    @Email
    private String email;

    @NotBlank(message = "{auth.password.required}")
    @Size(min = 8, max = 255, message = "{auth.password.size}")
    @Pattern(regexp = RegexPattern.PASSWORD, message = "{auth.password.pattern}")
    private String password;
}

package com.xsoci.backend.dto.request;

import lombok.Data;
import jakarta.validation.constraints.*;
import com.xsoci.backend.constant.RegexPattern;

@Data
public class ChangePasswordRequest {
    @NotBlank(message = "{auth.password.required}")
    @Size(min = 8, max = 255, message = "{auth.password.size}")
    @Pattern(regexp = RegexPattern.PASSWORD, message = "{auth.password.pattern}")
    private String currentPassword;

    @NotBlank(message = "{auth.password.required}")
    @Size(min = 8, max = 255, message = "{auth.password.size}")
    @Pattern(regexp = RegexPattern.PASSWORD, message = "{auth.password.pattern}")
    private String newPassword;

    @NotBlank(message = "{auth.confirm-password.required}")
    @Size(min = 8, max = 255, message = "{auth.confirm-password.size}")
    @Pattern(regexp = RegexPattern.PASSWORD, message = "{auth.confirm-password.pattern}")
    private String confirmNewPassword;

    @AssertTrue(message = "{auth.confirm-password.not_match}")
    public boolean isPasswordMatching() {
        return newPassword != null && newPassword.equals(confirmNewPassword);
    }
}
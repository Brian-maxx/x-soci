package com.xsoci.backend.dto.request;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ForgotPasswordRequest {
    @NotBlank(message = "{auth.email.required}")
    @Email
    private String email;
}

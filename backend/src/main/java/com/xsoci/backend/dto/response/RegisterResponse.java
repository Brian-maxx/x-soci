package com.xsoci.backend.dto.response;

import lombok.Data;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private int statusCode;
    private String statusMessage;
    private UserResponse user;
}
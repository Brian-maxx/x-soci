package com.xsoci.backend.util;

import org.springframework.http.HttpStatus;

import com.xsoci.backend.dto.response.MessageResponse;

public class ResponseUtil {

    public static MessageResponse success(String message) {
        return MessageResponse.builder()
            .status(HttpStatus.OK.value())
            .message(message)
            .build();
    }
}

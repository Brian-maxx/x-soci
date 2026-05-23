package com.xsoci.backend.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
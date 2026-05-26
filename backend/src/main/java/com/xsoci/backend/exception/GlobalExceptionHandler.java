package com.xsoci.backend.exception;

import org.hibernate.TransientPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import com.xsoci.backend.constant.HttpConstants;
import com.xsoci.backend.util.MessageUtil;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageUtil messageUtil;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
            .getFieldErrors()
            .forEach(error -> {
                errors.putIfAbsent(
                    error.getField(),
                    error.getDefaultMessage()
                );
            });

        return ResponseEntity.badRequest().body(
                Map.of(
                    "status", HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    "error", HttpConstants.VALIDATION_ERROR,
                    "message", errors
                )
        );
    }

    @ExceptionHandler(TransientPropertyValueException.class)
    public ResponseEntity<?> handleHibernateError(TransientPropertyValueException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                    "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "error", HttpConstants.DATABASE_ERROR,
                    "message", messageUtil.getMessage("server.500")
                ));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                    "status", HttpStatus.BAD_REQUEST.value(),
                    "error", ex.getErrorCode(),
                    "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        return ResponseEntity.status(500).body(
            Map.of(
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", HttpConstants.HTTP_500,
                "message", messageUtil.getMessage("server.500")
            )
        );
    }

}
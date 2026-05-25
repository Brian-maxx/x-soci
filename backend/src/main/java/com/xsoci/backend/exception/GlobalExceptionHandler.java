package com.xsoci.backend.exception;

import org.hibernate.TransientPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(
                Map.of(
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
                        "error", HttpConstants.DATABASE_ERROR,
                        "message", messageUtil.getMessage("server.500")
                ));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", ex.getErrorCode(),
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        return ResponseEntity.status(500).body(
            Map.of(
                "error", HttpConstants.HTTP_500,
                "message", messageUtil.getMessage("server.500")
            )
        );
    }

}
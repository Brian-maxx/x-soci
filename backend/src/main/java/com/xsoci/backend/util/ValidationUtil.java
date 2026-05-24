package com.xsoci.backend.util;

import org.springframework.stereotype.Component;
import com.xsoci.backend.exception.CustomException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

    private final MessageUtil messageUtil;

    public void throwExists(String field) {
        throw new CustomException(
            messageUtil.getMessage(
                "validation.exists",
                new Object[]{field}
            )
        );
    }

    public void throwNotMatch(String field1, String field2) {
        throw new CustomException(
            messageUtil.getMessage(
                "validation.not_match",
                new Object[]{field1, field2}
            )
        );
    }

    public void throwNotFound(String field) {
        throw new CustomException(
            messageUtil.getMessage(
                "validation.not_found",
                new Object[]{field}
            )
        );
    }

    public void throwInvalid(String field) {
        throw new CustomException(
            messageUtil.getMessage(
                "validation.invalid",
                new Object[]{field}
            )
        );
    }

    public void throwInactive(String field) {
        throw new CustomException(
            messageUtil.getMessage(
                "validation.inactive",
                new Object[]{field}
            )
        );
    }

    public void throwIsDeleted(String field) {
        throw new CustomException(
            messageUtil.getMessage(
                "validation.is_deleted",
                new Object[]{field}
            )
        );
    }

    public void throwNotAllowed(String field) {
        throw new CustomException(
            messageUtil.getMessage(
                "validation.not_allowed",
                new Object[]{field}
            )
        );
    }
}
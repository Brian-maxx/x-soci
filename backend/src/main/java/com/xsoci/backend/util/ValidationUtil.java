package com.xsoci.backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.xsoci.backend.constant.HttpConstants;
import com.xsoci.backend.exception.CustomException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

    private final MessageUtil messageUtil;

    public void throwExists(String field) {
        throw new CustomException(
            HttpConstants.HTTP_400,
            messageUtil.getMessage(
                "validation.exists",
                new Object[]{field}
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    public void throwNotMatch(String field1, String field2) {
        throw new CustomException(
            HttpConstants.HTTP_404,
            messageUtil.getMessage(
                "validation.not_match",
                new Object[]{field1, field2}
            ),
            HttpStatus.NOT_FOUND
        );
    }

    public void throwNotFound(String field) {
        throw new CustomException(
            HttpConstants.HTTP_400,
            messageUtil.getMessage(
                "validation.not_found",
                new Object[]{field}
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    public void throwInvalid(String field) {
        throw new CustomException(
            HttpConstants.HTTP_400,
            messageUtil.getMessage(
                "validation.invalid",
                new Object[]{field}
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    public void throwInactive(String field) {
        throw new CustomException(
            HttpConstants.HTTP_400,
            messageUtil.getMessage(
                "validation.inactive",
                new Object[]{field}
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    public void throwIsDeleted(String field) {
        throw new CustomException(
            HttpConstants.HTTP_400,
            messageUtil.getMessage(
                "validation.is_deleted",
                new Object[]{field}
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    public void throwNotAllowed(String field) {
        throw new CustomException(
            HttpConstants.HTTP_403,
            messageUtil.getMessage(
                "validation.not_allowed",
                new Object[]{field}
            ),
            HttpStatus.FORBIDDEN
        );
    }
}
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
}
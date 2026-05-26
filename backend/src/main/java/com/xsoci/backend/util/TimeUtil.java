package com.xsoci.backend.util;

import java.time.LocalDateTime;

import com.xsoci.backend.constant.VariableConstants;

public class TimeUtil {
    public static LocalDateTime generateDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDateTime expireDateTime() {
        return LocalDateTime.now().plusMinutes(VariableConstants.EXPIRATION_MINUTES);
    }
}

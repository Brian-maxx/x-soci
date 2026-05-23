package com.xsoci.backend.constant;

public final class RegexPattern {

    private RegexPattern() {}

    public static final String USERNAME = "^[a-zA-Z0-9]+$";
    public static final String PASSWORD =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final String PHONE_NUMBER = "^[0-9]{10}$";
}
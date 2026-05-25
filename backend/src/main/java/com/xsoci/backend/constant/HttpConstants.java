package com.xsoci.backend.constant;

public final class HttpConstants {

    private HttpConstants() {}

    // =========================
    // SUCCESS
    // =========================
    public static final String HTTP_200 = "SUCCESS";
    public static final String HTTP_201 = "CREATED";
    public static final String HTTP_204 = "NO_CONTENT";

    // =========================
    // CLIENT ERRORS
    // =========================
    public static final String HTTP_400 = "BAD_REQUEST";
    public static final String HTTP_401 = "UNAUTHORIZED";
    public static final String HTTP_403 = "FORBIDDEN";
    public static final String HTTP_404 = "NOT_FOUND";
    public static final String HTTP_405 = "METHOD_NOT_ALLOWED";
    public static final String HTTP_409 = "CONFLICT";
    public static final String HTTP_422 = "UNPROCESSABLE_ENTITY";

    // =========================
    // SERVER ERRORS
    // =========================
    public static final String HTTP_500 = "INTERNAL_SERVER_ERROR";
    public static final String HTTP_502 = "BAD_GATEWAY";
    public static final String HTTP_503 = "SERVICE_UNAVAILABLE";

    // =========================
    // VALIDATION / AUTH
    // =========================
    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String DATABASE_ERROR = "DATABASE_ERROR";
    public static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
    public static final String INVALID_TOKEN = "INVALID_TOKEN";
    public static final String ACCESS_DENIED = "ACCESS_DENIED";
}
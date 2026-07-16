package com.example.scan3d.models.response;

public enum ErrorCode {
    INTERNAL_ERROR("INTERNAL_ERROR"),
    VALIDATION_ERROR("VALIDATION_ERROR"),
    UNAUTHORIZED("UNAUTHORIZED"),
    FORBIDDEN("FORBIDDEN"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND"),
    RESOURCE_ALREADY_EXISTS("RESOURCE_ALREADY_EXISTS"),
    BAD_REQUEST("BAD_REQUEST"),
    INVALID_PARAMETER("INVALID_PARAMETER");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

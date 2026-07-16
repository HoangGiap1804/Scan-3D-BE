package com.example.scan3d.models.response.errors;

import com.example.scan3d.models.response.ErrorCode;
import com.example.scan3d.models.response.StatusCode;

public class BaseRequestException extends RuntimeException {

    private final StatusCode statusCode;
    private final ErrorCode errorCode;

    public BaseRequestException(String message, StatusCode statusCode, ErrorCode errorCode) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public BaseRequestException(String message, StatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = mapDefaultErrorCode(statusCode);
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private static ErrorCode mapDefaultErrorCode(StatusCode statusCode) {
        return switch (statusCode) {
            case BAD_REQUEST -> ErrorCode.BAD_REQUEST;
            case UNAUTHORIZED -> ErrorCode.UNAUTHORIZED;
            case FORBIDDEN -> ErrorCode.FORBIDDEN;
            case NOT_FOUND -> ErrorCode.RESOURCE_NOT_FOUND;
            default -> ErrorCode.INTERNAL_ERROR;
        };
    }
}

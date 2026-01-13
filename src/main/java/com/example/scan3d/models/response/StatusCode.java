package com.example.scan3d.models.response;

public enum StatusCode {
    UNKNOWN_ERROR(500),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    OK(200),
    CREATED(201),
    DELETED(204);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

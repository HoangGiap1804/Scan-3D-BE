package com.example.scan3d.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessResponse<T> extends BaseResponse {

    private final String message;
    private final T data;

    public SuccessResponse(T data) {
        this(data, "OK");
    }

    public SuccessResponse(T data, String message) {
        super(true);
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
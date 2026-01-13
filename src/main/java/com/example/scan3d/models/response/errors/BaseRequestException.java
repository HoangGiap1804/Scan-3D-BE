package com.example.scan3d.models.response.errors;

import com.example.scan3d.models.response.StatusCode;

public class BaseRequestException extends RuntimeException{
    private StatusCode statusCode;
    public BaseRequestException(String message, StatusCode statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}

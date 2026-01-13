package com.example.scan3d.models.response.errors;

import com.example.scan3d.models.response.StatusCode;

public class UnauthorizedRequestException extends BaseRequestException   {
    public UnauthorizedRequestException(String message){
        super(message, StatusCode.UNAUTHORIZED);
    }
}
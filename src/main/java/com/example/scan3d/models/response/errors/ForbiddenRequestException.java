package com.example.scan3d.models.response.errors;

import com.example.scan3d.models.response.StatusCode;

public class ForbiddenRequestException extends BaseRequestException   {
    public ForbiddenRequestException(String message){
        super(message, StatusCode.FORBIDDEN);
    }
}
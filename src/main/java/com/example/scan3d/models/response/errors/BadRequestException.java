package com.example.scan3d.models.response.errors;

import com.example.scan3d.models.response.StatusCode;

public class BadRequestException extends BaseRequestException   {
    public BadRequestException(String message){
        super(message, StatusCode.BAD_REQUEST);
    }
}

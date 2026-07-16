package com.example.scan3d.models.response.errors;

import com.example.scan3d.models.response.ErrorCode;
import com.example.scan3d.models.response.StatusCode;

public class NotFoundException extends BaseRequestException {

    public NotFoundException(String message) {
        super(message, StatusCode.NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND);
    }
}

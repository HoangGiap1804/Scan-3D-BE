package com.example.scan3d.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage", "message", "suppressedExceptions"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends BaseResponse {

    private final String error;
    private final String code;
    private final LocalDateTime timestamp;
    private final String path;
    private Map<String, Object> details;

    public ErrorResponse(String error, String code, String path) {
        super(false);
        this.error = error;
        this.code = code;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }

    public ErrorResponse(String error, String code, String path, Map<String, Object> details) {
        super(false);
        this.error = error;
        this.code = code;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.details = details;
    }

    public String getError() {
        return error;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
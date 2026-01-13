package com.example.scan3d.models.response;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage", "message", "suppressedExceptions"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse extends BaseResponse{

    private final String error;
    private Map<String, Object> details;

    public ErrorResponse(String error) {
        super(false);
        this.error = error;
    }

    public ErrorResponse(String error, Map<String, Object> details) {
        super(false);
        this.error = error;
        this.details = details;
    }
    
    public String getError() {
        return error;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}

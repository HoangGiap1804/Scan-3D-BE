package com.example.scan3d.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.scan3d.models.response.ErrorCode;
import com.example.scan3d.models.response.ErrorResponse;
import com.example.scan3d.models.response.errors.BaseRequestException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CustomeExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles all custom business exceptions (BadRequest, NotFound, Forbidden, Unauthorized, etc.)
     * by catching the base class instead of each subclass individually.
     */
    @ExceptionHandler(BaseRequestException.class)
    public ResponseEntity<ErrorResponse> handleBaseRequestException(BaseRequestException ex, HttpServletRequest request) {
        log.warn("Business exception on [{}]: {}", request.getRequestURI(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                ex.getErrorCode().getCode(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode().getCode()));
    }

    /**
     * Handles @Valid / @Validated annotation validation failures on @RequestBody.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        String path = getPath(request);
        ErrorResponse errorResponse = new ErrorResponse(
                "Validation Failed",
                ErrorCode.VALIDATION_ERROR.getCode(),
                path,
                errors
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles @Validated on @PathVariable / @RequestParam (ConstraintViolation).
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String propertyPath = violation.getPropertyPath().toString();
            String field = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
            errors.put(field, violation.getMessage());
        });
        ErrorResponse errorResponse = new ErrorResponse(
                "Validation Failed",
                ErrorCode.VALIDATION_ERROR.getCode(),
                request.getRequestURI(),
                errors
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Fallback handler for all unhandled exceptions.
     * IMPORTANT: Never expose internal error details to the client.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, HttpServletRequest request) {
        // Full details logged on server only
        log.error("Unhandled exception on [{}]", request.getRequestURI(), ex);
        ErrorResponse errorResponse = new ErrorResponse(
                "An unexpected error occurred. Please try again later.",
                ErrorCode.INTERNAL_ERROR.getCode(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getPath(WebRequest request) {
        if (request instanceof ServletWebRequest servletWebRequest) {
            return servletWebRequest.getRequest().getRequestURI();
        }
        return "unknown";
    }
}

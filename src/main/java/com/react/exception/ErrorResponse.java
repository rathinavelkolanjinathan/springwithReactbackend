package com.react.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        Class errorClass,
        String message,
        int status,
        LocalDateTime timestamp)
{
    public ErrorResponse(Class errorClass, String message, int status) {
        this(errorClass, message, status, LocalDateTime.now());
    }
}

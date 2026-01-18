package com.mediconnect.service.common_entities.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {

    public InvalidCredentialsException() {
        super(
                "Invalid Credentials provided",
                HttpStatus.UNAUTHORIZED,
                "INVALID_CREDENTIALS"
        );
    }

    public InvalidCredentialsException(String message, HttpStatus httpStatus) {
        super(
                message,
                httpStatus,
                "INVALID DATA PROVIDED"
        );
    }
}

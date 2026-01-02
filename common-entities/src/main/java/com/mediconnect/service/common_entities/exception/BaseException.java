package com.mediconnect.service.common_entities.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter // why is it mandatory to have getter here?
public abstract class BaseException extends RuntimeException { // should be abstract, since it is base class

    private final HttpStatus status;
    private final String errorCode;


    public BaseException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }
}

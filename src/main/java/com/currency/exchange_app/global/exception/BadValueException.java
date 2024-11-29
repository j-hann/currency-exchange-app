package com.currency.exchange_app.global.exception;

import lombok.Getter;

@Getter
public class BadValueException extends BusinessException {
    private final ExceptionType exceptionType;

    public BadValueException(final ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }


}

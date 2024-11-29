package com.currency.exchange_app.global.exception;

import lombok.Getter;

@Getter
public class NotFoundByIdException extends BusinessException {
    private final ExceptionType exceptionType;

    public NotFoundByIdException(final ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }


}

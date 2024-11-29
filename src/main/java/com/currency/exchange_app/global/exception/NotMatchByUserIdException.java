package com.currency.exchange_app.global.exception;

import lombok.Getter;

@Getter
public class NotMatchByUserIdException extends BusinessException {
    private final ExceptionType exceptionType;

    public NotMatchByUserIdException(final ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }


}

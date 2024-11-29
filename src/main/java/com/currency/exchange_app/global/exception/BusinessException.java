package com.currency.exchange_app.global.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {
    private ExceptionType exceptionType;
}

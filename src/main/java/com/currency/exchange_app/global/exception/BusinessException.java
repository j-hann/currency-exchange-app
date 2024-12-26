package com.currency.exchange_app.global.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ExceptionType exceptionType;

	public BusinessException(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}
}

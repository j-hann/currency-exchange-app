package com.currency.exchange_app.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {


    EXIST_USER(HttpStatus.BAD_REQUEST, "동일한 email의 사용자가 존재합니다."),
    USER_NOT_MATCH(HttpStatus.BAD_REQUEST, "잘못된 유저의 정보에 접근하고 있습니다."),
    EMAIL_NOT_MATCH(HttpStatus.BAD_REQUEST, "잘못된 이메일 입니다. 다시 입력해주세요."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,  "해당 유저를 찾을 수 없습니다."),
    CURRENCY_NOT_FOUND(HttpStatus.NOT_FOUND,  "해당 통화를 찾을 수 없습니다."),
    EXCHANGE_NOT_FOUND(HttpStatus.NOT_FOUND,  "해당 환전 요청 데이터를 찾을 수 없습니다."),
    WRONG_EXCHANGE_RATE(HttpStatus.BAD_REQUEST, "잘못된 환율값 입니다.");

    private final HttpStatus status;
    private final String message;

    ExceptionType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}

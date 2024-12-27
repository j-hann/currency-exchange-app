package com.currency.exchange_app.domain.base.enums;

import lombok.Getter;

@Getter
public enum CurrencyStatus {
//        KRW("KRW", "한화", "₩"),
        USD("USD", "달러", "$"),
        JPY("JPY", "엔화", "円"),
        CNY("CNY", "위안", "元");

        private final String code;//통화 코드
        private final String name;//통화 코드 이름
        private final String symbol;//통화 기호

    CurrencyStatus(String code, String name, String symbol) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }
}

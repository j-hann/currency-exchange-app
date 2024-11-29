package com.currency.exchange_app.domain.base.enums;

import lombok.Getter;

@Getter
public enum ExchangeStatus {
    NORMAL(1, "정상"), CANCELLED(0, "취소");

    private final int statusNumber;
    private final String statusText;

    ExchangeStatus(int statusNumber, String statusText) {
        this.statusNumber = statusNumber;
        this.statusText = statusText;
    }
}

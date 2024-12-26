package com.currency.exchange_app.domain.currency.dto;

import com.currency.exchange_app.domain.base.enums.CurrencyStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotNull
    private final CurrencyStatus currencyStatus;//통화 상태(코드, 이름)

    @NotNull
    private final BigDecimal exchangeRate;//환율

    public CurrencyRequestDto(CurrencyStatus currencyStatus, BigDecimal exchangeRate) {
        this.currencyStatus = currencyStatus;
        this.exchangeRate = exchangeRate;
    }
}

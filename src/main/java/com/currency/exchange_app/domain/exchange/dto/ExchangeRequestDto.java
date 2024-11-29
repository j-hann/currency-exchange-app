package com.currency.exchange_app.domain.exchange.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    @NotNull
    private final Long userId;//사용자 id

    @NotNull
    private final Long currencyId;//통화 id

    @NotNull
    private final BigDecimal amountBeforeExchange;//환전 전 금액

    public ExchangeRequestDto(Long userId, Long currencyId, BigDecimal amountBeforeExchange) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountBeforeExchange = amountBeforeExchange;
    }
}

package com.currency.exchange_app.domain.exchange.dto;


import com.currency.exchange_app.domain.base.enums.ExchangeStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateExchangeRequestDto {

    @NotNull
    private ExchangeStatus exchangeStatus;//환전 상태

    public UpdateExchangeRequestDto(Long exchangeId, ExchangeStatus exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }
}

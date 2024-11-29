package com.currency.exchange_app.domain.currency.dto;

import com.currency.exchange_app.domain.base.enums.CurrencyStatus;
import com.currency.exchange_app.domain.base.enums.ExchangeStatus;
import com.currency.exchange_app.domain.currency.entity.Currency;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class CurrencyResponseDto {
    private final Long id;//통화 id
    private final CurrencyStatus currencyStatus;//통화 상태
    private final String currencyName; // 통화 이름
    private final String currencySymbol; // 통화 기호
    private final BigDecimal exchangeRate;//환율
    private final LocalDateTime createdAt;//생성일
    private final LocalDateTime modifiedAt;//수정일


    public CurrencyResponseDto(Long id, CurrencyStatus currencyStatus,  String currencyName, String currencySymbol, BigDecimal exchangeRate, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.currencyStatus = currencyStatus;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.exchangeRate = exchangeRate;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CurrencyResponseDto toDto(Currency currency) {

        return new CurrencyResponseDto(
                currency.getId(),
                currency.getCurrencyStatus(),
                currency.getCurrencyStatus().getName(),
                currency.getCurrencyStatus().getSymbol(),
                currency.getExchangeRate(),
                currency.getCreatedAt(),
                currency.getModifiedAt()
        );
    }


}

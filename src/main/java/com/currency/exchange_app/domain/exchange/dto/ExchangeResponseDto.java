package com.currency.exchange_app.domain.exchange.dto;

import com.currency.exchange_app.domain.base.enums.ExchangeStatus;
import com.currency.exchange_app.domain.exchange.entity.Exchange;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ExchangeResponseDto {

    private final Long id;//화폐 id

    private final Long userId;//사용자 id(외래키)

    private final BigDecimal exchangeRate;//환율

    private final BigDecimal amountBeforeExchange;//환전 전 금액

    private final BigDecimal amountAfterExchange;//환전 후 금액

    private final ExchangeStatus exchangeStatus;//환전 상태

    private final LocalDateTime createdAt;//생성일

    private final LocalDateTime modifiedAt;//수정일




    public ExchangeResponseDto(Long id, Long userId, BigDecimal exchangeRate, BigDecimal amountBeforeExchange, BigDecimal amountAfterExchange, ExchangeStatus exchangeStatus,  LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.exchangeRate = exchangeRate;
        this.amountBeforeExchange = amountBeforeExchange;
        this.amountAfterExchange = amountAfterExchange;
        this.exchangeStatus = exchangeStatus;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ExchangeResponseDto toDto(Exchange exchange) {
        return new ExchangeResponseDto(
                exchange.getId(),
                exchange.getUser().getId(),
                exchange.getCurrency().getExchangeRate(),
                exchange.getAmountBeforeExchange(),
                exchange.getAmountAfterExchange(),
                exchange.getExchangeStatus(),
                exchange.getCreatedAt(),
                exchange.getModifiedAt()
        );
    }

}

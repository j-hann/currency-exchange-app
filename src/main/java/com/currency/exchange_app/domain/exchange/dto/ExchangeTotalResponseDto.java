package com.currency.exchange_app.domain.exchange.dto;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class ExchangeTotalResponseDto {

		private final Long userId;

		private final Long count;//환전 요청 횟수

		private final BigDecimal totalAmount;//요청한 총 환전 금액

	public ExchangeTotalResponseDto(Long userId, Long count, BigDecimal totalAmount) {
		this.userId = userId;
		this.count = count;
		this.totalAmount = totalAmount;
	}
}

package com.currency.exchange_app.global;

import com.currency.exchange_app.domain.currency.entity.Currency;
import com.currency.exchange_app.domain.currency.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExchangeRateCheck {

	private final CurrencyRepository currencyRepository;
	//환율 최솟값, 최댓값 지정
	private static final BigDecimal minValue = new BigDecimal("1");
	private static final BigDecimal maxValue = new BigDecimal("2000");

	@Autowired
	public ExchangeRateCheck(CurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	/**
	 *- 어플리케이션 구동시 자동으로 한번 실행
	 *
	 */
	@PostConstruct
	private void exchangeRateCheck(){
		//통화 조회
		List<Currency> currency = currencyRepository.findAll();
		//지정된 범위를 벗어나는 환율 값 저장
		List<Currency> savedCurrency = currency.stream().filter(currencies -> verifyExchangeRateRange(currencies.getExchangeRate())).toList();

		//savedCurrency에 값이 있을 경우 -> List 순회하면서 로그 기록
		if (!savedCurrency.isEmpty()){
			savedCurrency.forEach( currencies -> log.error("환율에 잘못된 값이 저장되었습니다. [환율 id : {}, 환율 값 : {}]", currencies.getId(), currencies.getExchangeRate()));
		}
	}

	/**
	 *환율 검증 메서드
	 * - 0보다 작거나 같다 or 최솟값 1 보다 작다 or 최댓값 2000 보다 크다
	 */
	private boolean verifyExchangeRateRange(BigDecimal exchangeRate) {
		return exchangeRate.compareTo(BigDecimal.ZERO) <= 0 || exchangeRate.compareTo(minValue) < 0 || exchangeRate.compareTo(maxValue) > 0;
	}
}

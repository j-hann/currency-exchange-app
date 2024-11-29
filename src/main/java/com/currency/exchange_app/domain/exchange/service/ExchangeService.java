package com.currency.exchange_app.domain.exchange.service;

import com.currency.exchange_app.domain.base.enums.ExchangeStatus;
import com.currency.exchange_app.domain.currency.dto.CurrencyResponseDto;
import com.currency.exchange_app.domain.currency.entity.Currency;
import com.currency.exchange_app.domain.currency.repository.CurrencyRepository;
import com.currency.exchange_app.domain.exchange.dto.ExchangeRequestDto;
import com.currency.exchange_app.domain.exchange.dto.ExchangeResponseDto;
import com.currency.exchange_app.domain.exchange.entity.Exchange;
import com.currency.exchange_app.domain.exchange.repository.ExchangeRepository;
import com.currency.exchange_app.domain.user.entity.User;
import com.currency.exchange_app.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    //통화 교환
    public ExchangeResponseDto exchangeCurrency(Long userId, Long currencyId, BigDecimal amountBeforeExchange) {
        //사용자 id 조회
        User findUserById = userRepository.findByIdOrElseThrow(userId);

        //통화 id 조회
        Currency findCurrencyById = currencyRepository.findCurrencyByIdOrElseThrow(currencyId);

        //환율
        BigDecimal exchangeRate = findCurrencyById.getExchangeRate();
        //환전 후 금액
        BigDecimal amountAfterExchange = amountBeforeExchange.divide(exchangeRate, 2, RoundingMode.HALF_UP);//소수점 두번째 반올림 수행

        Exchange exchange = new Exchange(findUserById, findCurrencyById, amountBeforeExchange,  amountAfterExchange, ExchangeStatus.NORMAL);

        //저장
        Exchange saveExchangeCurrency = exchangeRepository.save(exchange);

        return ExchangeResponseDto.toDto(saveExchangeCurrency);
    }

    //특정 사용자 통화 교환 리스트
    public List<ExchangeResponseDto> findAllByExchangeList(Long userId) {
        return exchangeRepository.findByUserId(userId)
                .stream()
                .map(ExchangeResponseDto::toDto).toList();
    }

    //통화 교환 리스트
    public List<ExchangeResponseDto> findAllExchangeList() {
        return exchangeRepository.findAll()
                .stream()
                .map(ExchangeResponseDto::toDto).toList();
    }

}

package com.currency.exchange_app.domain.exchange.service;

import com.currency.exchange_app.domain.base.enums.ExchangeStatus;
import com.currency.exchange_app.domain.currency.entity.Currency;
import com.currency.exchange_app.domain.currency.repository.CurrencyRepository;
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

    //환전 요청
    public ExchangeResponseDto exchangeCurrency(Long userId, Long currencyId, BigDecimal amountBeforeExchange) {
        //사용자 id 조회
        User findUserById = userRepository.findByIdOrElseThrow(userId);

        //통화 id 조회
        Currency findCurrencyById = currencyRepository.findCurrencyByIdOrElseThrow(currencyId);

        //환율
        BigDecimal exchangeRate = findCurrencyById.getExchangeRate();
        //환전 후 금액
        BigDecimal amountAfterExchange = amountBeforeExchange.divide(exchangeRate, 2, RoundingMode.HALF_UP);//소수점 두번째 반올림 수행

        //상태 NORMAL
        Exchange exchange = new Exchange(findUserById, findCurrencyById, amountBeforeExchange, amountAfterExchange, ExchangeStatus.NORMAL);

        //저장
        Exchange saveExchangeCurrency = exchangeRepository.save(exchange);

        return ExchangeResponseDto.toDto(saveExchangeCurrency);
    }

    //특정 사용자 환전 요청 리스트
    public List<ExchangeResponseDto> findAllByExchangeList(Long userId) {
        return exchangeRepository.findByUserId(userId)
                .stream()
                .map(ExchangeResponseDto::toDto).toList();
    }

    //환전 요청 리스트
    public List<ExchangeResponseDto> findAllExchangeList() {
        return exchangeRepository.findAll()
                .stream()
                .map(ExchangeResponseDto::toDto).toList();
    }


    //환전 요청 상태 수정 (환전 요청 취소)
//    @Transactional
    public ExchangeResponseDto updateExchangeById(Long exchangeId) {

        //환전 요청 id 조회
        Exchange updateExchange = exchangeRepository.findByExchangeIdOrElseThrow(exchangeId);

        //상태 NORMAL -> CANCELLED 수정
//        exchange.updateExchange(ExchangeStatus.CANCELLED);
//
//        //수정된 상태 저장
//        Exchange saveExchange = exchangeRepository.save(exchange);
//
//        //Dto 반환
//        return ExchangeResponseDto.toDto(saveExchange);

        // 상태 변경: NORMAL -> CANCELLED
        if (updateExchange.getExchangeStatus().equals(ExchangeStatus.NORMAL)) {
            updateExchange.updateExchange(ExchangeStatus.CANCELLED);
        }

        return ExchangeResponseDto.toDto(updateExchange);

    }
}
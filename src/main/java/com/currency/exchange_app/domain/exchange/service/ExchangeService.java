package com.currency.exchange_app.domain.exchange.service;

import com.currency.exchange_app.domain.base.enums.CurrencyStatus;
import com.currency.exchange_app.domain.base.enums.ExchangeStatus;
import com.currency.exchange_app.domain.currency.entity.Currency;
import com.currency.exchange_app.domain.currency.repository.CurrencyRepository;
import com.currency.exchange_app.domain.exchange.dto.ExchangeResponseDto;
import com.currency.exchange_app.domain.exchange.dto.ExchangeTotalResponseDto;
import com.currency.exchange_app.domain.exchange.entity.Exchange;
import com.currency.exchange_app.domain.exchange.repository.ExchangeRepository;
import com.currency.exchange_app.domain.user.entity.User;
import com.currency.exchange_app.domain.user.repository.UserRepository;
import com.currency.exchange_app.global.exception.BusinessException;
import com.currency.exchange_app.global.exception.ExceptionType;
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

    /**
     * 사용자 환전 요청 API
     *
     */
    public ExchangeResponseDto exchangeCurrency(Long userId, Long currencyId, BigDecimal amountBeforeExchange) {
        //사용자 id 조회
        User findUserById = userRepository.findByIdOrElseThrow(userId);
        //통화 id 조회
        Currency currency = currencyRepository.findCurrencyByIdOrElseThrow(currencyId);

        //환율
        BigDecimal exchangeRate = currency.getExchangeRate();
        //환전 후 금액
        BigDecimal amountAfterExchange = amountAfterExchangeCurrency(currency.getCurrencyStatus(), exchangeRate, amountBeforeExchange);

        //상태 NORMAL
        Exchange exchange = new Exchange(findUserById, currency, amountBeforeExchange, amountAfterExchange, ExchangeStatus.NORMAL);
        //저장
        Exchange saveExchangeCurrency = exchangeRepository.save(exchange);

        return ExchangeResponseDto.toDto(saveExchangeCurrency);
    }

    /**
     * 환전 소수점 계산
     */
    public BigDecimal amountAfterExchangeCurrency( CurrencyStatus currencyStatus, BigDecimal exchangeRate, BigDecimal amountBeforeExchange) {

        switch (currencyStatus){
            case USD:
                return amountBeforeExchange.divide(exchangeRate, 2, RoundingMode.HALF_UP);//소수점 두번째 반올림

            case JPY :
                return amountBeforeExchange.divide(exchangeRate, RoundingMode.DOWN);//내림

            case CNY :
                return amountBeforeExchange.divide(exchangeRate, 2, RoundingMode.FLOOR);//소수점 두번째 이하 버리기

			default :
                throw new BusinessException(ExceptionType.WRONG_EXCHANGE_RATE);
        }

    }

    /**
     * 사용자 환전 요청 내역 전체 조회 API
     *
     */
    public List<ExchangeResponseDto> findAllExchangeList(Long userId) {
        return exchangeRepository.findByUserId(userId)
                .stream()
                .map(ExchangeResponseDto::toDto).toList();
    }

    /**
     * 사용자 환전 요청 내역 그룹 조회 API
     * - 총 환전 횟수, 총 환전 요청한 금액
     */
    public ExchangeTotalResponseDto findAllExchangeTotalAmount(Long userId) {

        ExchangeTotalResponseDto exchangeTotalResponseDto = exchangeRepository.findByIdExchangeTotalAmount(userId);

        return exchangeTotalResponseDto;
    }

    /**
     * 환전 요청 상태 수정 API
     * - 환전 요청 취소 CANCELLED
     */
    public ExchangeResponseDto updateExchange(Long exchangeId, ExchangeStatus exchangeStatus) {

        //환전 요청 id null값 확인
        if (exchangeId == null) {
            throw new BusinessException(ExceptionType.EXCHANGE_NOT_FOUND);
        }

        //환전 요청 id 조회
        Exchange updateExchange = exchangeRepository.findByExchangeIdOrElseThrow(exchangeId);

        // 상태 변경: NORMAL -> CANCELLED
        if (updateExchange.getExchangeStatus().equals(ExchangeStatus.NORMAL)) {
            updateExchange.updateExchange(ExchangeStatus.CANCELLED);
            exchangeRepository.save(updateExchange);
        }

        return ExchangeResponseDto.toDto(updateExchange);

    }

}

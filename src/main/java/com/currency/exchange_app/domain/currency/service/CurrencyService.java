package com.currency.exchange_app.domain.currency.service;

import com.currency.exchange_app.domain.base.enums.CurrencyStatus;
import com.currency.exchange_app.domain.currency.dto.CurrencyResponseDto;
import com.currency.exchange_app.domain.currency.entity.Currency;
import com.currency.exchange_app.domain.currency.repository.CurrencyRepository;
import com.currency.exchange_app.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;


    //달러 통화 생성
    public CurrencyResponseDto create(CurrencyStatus currencyStatus, BigDecimal exchangeRate) {

        //통화 생성
        Currency currency = new Currency(currencyStatus, exchangeRate);

        Currency saveCurrency = currencyRepository.save(currency);

        return CurrencyResponseDto.toDto(saveCurrency);
    }

    //통화 전체 리스트 조회
    public List<CurrencyResponseDto> findAllCurrencyList(){
        return currencyRepository.findAll()
                .stream()
                .map(CurrencyResponseDto::toDto).toList();
    };

    //통화 삭제
    public void delete(Long id) {
        //통화 id 조회
        Currency findCurrencyById = currencyRepository.findCurrencyByIdOrElseThrow(id);

        //통화 삭제
        currencyRepository.delete(findCurrencyById);
    }

}

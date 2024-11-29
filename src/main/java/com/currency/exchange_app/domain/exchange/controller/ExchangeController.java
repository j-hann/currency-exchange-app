package com.currency.exchange_app.domain.exchange.controller;

import com.currency.exchange_app.domain.currency.dto.CurrencyRequestDto;
import com.currency.exchange_app.domain.currency.dto.CurrencyResponseDto;
import com.currency.exchange_app.domain.currency.service.CurrencyService;
import com.currency.exchange_app.domain.exchange.dto.ExchangeRequestDto;
import com.currency.exchange_app.domain.exchange.dto.ExchangeResponseDto;
import com.currency.exchange_app.domain.exchange.service.ExchangeService;
import com.currency.exchange_app.domain.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("users/{userId}/exchanges")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    //통화 교환
    @PostMapping("/create")
    public ResponseEntity<ExchangeResponseDto> exchangeCurrency(@PathVariable Long userId, @Valid @RequestBody ExchangeRequestDto requestDto) throws IOException {
        ExchangeResponseDto exchangeResponseDto = exchangeService.exchangeCurrency(
                userId,
                requestDto.getCurrencyId(),
                requestDto.getAmountBeforeExchange()
        );

        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.CREATED);
    }

    //통화 교환 전체 조회
    @GetMapping
    public ResponseEntity<List<ExchangeResponseDto>> findAllExchangeList() {

        List<ExchangeResponseDto> exchangeResponseDtoList =
                exchangeService.findAllExchangeList();

        return  new ResponseEntity<>(exchangeResponseDtoList, HttpStatus.OK);
    }
    
    //통화 교환 요청 수정


}


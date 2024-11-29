package com.currency.exchange_app.domain.exchange.controller;


import com.currency.exchange_app.domain.exchange.dto.ExchangeRequestDto;
import com.currency.exchange_app.domain.exchange.dto.ExchangeResponseDto;
import com.currency.exchange_app.domain.exchange.dto.UpdateExchangeRequestDto;
import com.currency.exchange_app.domain.exchange.service.ExchangeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    //환전 요청
    @PostMapping("users/{userId}/exchanges/create")
    public ResponseEntity<ExchangeResponseDto> exchangeCurrency(@PathVariable Long userId, @Valid @RequestBody ExchangeRequestDto requestDto) throws IOException {
        ExchangeResponseDto exchangeResponseDto = exchangeService.exchangeCurrency(
                userId,
                requestDto.getCurrencyId(),
                requestDto.getAmountBeforeExchange()
        );

        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.CREATED);
    }

    //환전 요청 전체 조회
    @GetMapping("/exchanges")
    public ResponseEntity<List<ExchangeResponseDto>> findAllExchangeList() {

        List<ExchangeResponseDto> exchangeResponseDtoList =
                exchangeService.findAllExchangeList();

        return  new ResponseEntity<>(exchangeResponseDtoList, HttpStatus.OK);
    }
    
    //환전 요청 상태 수정
    @PatchMapping("/exchanges/{exchangeId}")
    public ResponseEntity<ExchangeResponseDto> updateExchange( @PathVariable Long exchangeId , @Valid @RequestBody UpdateExchangeRequestDto requestDto) {

        ExchangeResponseDto exchangeResponseDto = exchangeService.updateExchange(
                exchangeId,
                requestDto.getExchangeStatus()
        );

        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.OK);
    }

}


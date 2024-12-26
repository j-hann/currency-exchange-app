package com.currency.exchange_app.domain.exchange.controller;

import com.currency.exchange_app.domain.exchange.dto.ExchangeRequestDto;
import com.currency.exchange_app.domain.exchange.dto.ExchangeResponseDto;
import com.currency.exchange_app.domain.exchange.dto.ExchangeTotalResponseDto;
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

    /**
     * 사용자 환전 요청 API
     *
     */
    @PostMapping("users/{userId}/exchanges")
    public ResponseEntity<ExchangeResponseDto> exchangeCurrency(@PathVariable Long userId, @Valid @RequestBody ExchangeRequestDto requestDto) throws IOException {
        ExchangeResponseDto exchangeResponseDto = exchangeService.exchangeCurrency(
                userId,
                requestDto.getCurrencyId(),
                requestDto.getAmountBeforeExchange()
        );

        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.CREATED);
    }


    /**
     * 사용자 환전 요청 내역 전체 조회 API
     * 
     */
    @GetMapping("users/{userId}/exchanges")
    public ResponseEntity<List<ExchangeResponseDto>> findAllExchangeList(@PathVariable Long userId) {

        List<ExchangeResponseDto> exchangeResponseDtoList =
            exchangeService.findAllExchangeList(userId);

        return  new ResponseEntity<>(exchangeResponseDtoList, HttpStatus.OK);
    }

    /**
     * 사용자 환전 요청 내역 그룹 조회 API
     * - 총 환전 횟수, 총 환전 요청한 금액
     */
    @GetMapping("users/{userId}/exchanges/total")
    public ResponseEntity<ExchangeTotalResponseDto> findAllExchangeTotalAmount(@PathVariable Long userId) {

        ExchangeTotalResponseDto exchangeTotalResponseDto =
                exchangeService.findAllExchangeTotalAmount(userId);

        return new ResponseEntity<>(exchangeTotalResponseDto, HttpStatus.OK);
    }

    /**
     * 환전 요청 상태 수정 API
     *
     */
    @PatchMapping("/exchanges/{exchangeId}")
    public ResponseEntity<ExchangeResponseDto> updateExchange( @PathVariable Long exchangeId , @Valid @RequestBody UpdateExchangeRequestDto requestDto) {

        ExchangeResponseDto exchangeResponseDto = exchangeService.updateExchange(
                exchangeId,
                requestDto.getExchangeStatus()
        );

        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.OK);
    }

}


package com.currency.exchange_app.domain.currency.controller;

import com.currency.exchange_app.domain.currency.dto.CurrencyRequestDto;
import com.currency.exchange_app.domain.currency.dto.CurrencyResponseDto;
import com.currency.exchange_app.domain.currency.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    //통화 생성
    @PostMapping("/create")
    public ResponseEntity<CurrencyResponseDto> create(@Valid @RequestBody CurrencyRequestDto requestDto) throws IOException {
        CurrencyResponseDto currencyResponseDto = currencyService.create(
                requestDto.getCurrencyStatus(),
                requestDto.getExchangeRate()
        );

        return new ResponseEntity<>(currencyResponseDto, HttpStatus.CREATED);
    }
    
    //통화 전체 리스트 조회
    @GetMapping
    public ResponseEntity<List<CurrencyResponseDto>> findAllCurrencyList() {

        List<CurrencyResponseDto> currencyResponseDtoList =
                currencyService.findAllCurrencyList();

        return  new ResponseEntity<>(currencyResponseDtoList, HttpStatus.OK);
    }

    //통화 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        currencyService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

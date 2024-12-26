package com.currency.exchange_app.domain.currency.entity;


import com.currency.exchange_app.domain.base.entity.BaseEntity;
import com.currency.exchange_app.domain.base.enums.CurrencyStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import java.math.BigDecimal;

//통화
@Entity
@Getter
public class Currency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//통화 id

    @Enumerated(EnumType.STRING) //Enum 문자열 저장
    @Column(name = "currency_status")
    private CurrencyStatus currencyStatus;//통화 상태(코드, 이름)

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;//환율

    //기본 생성자
    public Currency() {

    }

    public Currency(CurrencyStatus currencyStatus, BigDecimal exchangeRate) {
        this.currencyStatus = currencyStatus;
        this.exchangeRate = exchangeRate;
    }
}

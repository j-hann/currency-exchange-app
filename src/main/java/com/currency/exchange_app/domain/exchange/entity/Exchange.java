package com.currency.exchange_app.domain.exchange.entity;


import com.currency.exchange_app.domain.base.entity.BaseEntity;
import com.currency.exchange_app.domain.base.enums.ExchangeStatus;
import com.currency.exchange_app.domain.currency.entity.Currency;
import com.currency.exchange_app.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;

//환전 요청
@Entity
@Getter
@Table(name = "exchange")
public class Exchange extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//환전 요청 id

    @Column(name = "amount_before_exchange")
    private BigDecimal amountBeforeExchange;//환전 전 금액

    @Column(name = "amount_after_exchange")
    private BigDecimal amountAfterExchange;//환전 후 금액

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ExchangeStatus exchangeStatus;//환전 상태

    //N:1 관계
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//사용자 id(외래키)

    //N:1 관계
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;//통화 id(외래키)

    public Exchange() {

    }

    public Exchange(User user, Currency currency, BigDecimal amountBeforeExchange, BigDecimal amountAfterExchange, ExchangeStatus exchangeStatus) {
        this.user = user;
        this.currency = currency;
        this.amountBeforeExchange = amountBeforeExchange;
        this.amountAfterExchange = amountAfterExchange;
        this.exchangeStatus = exchangeStatus;
    }

    public Exchange(User user, Currency currency, BigDecimal amountAfterExchange, ExchangeStatus exchangeStatus) {
        this.user = user;
        this.currency = currency;
        this.amountAfterExchange = amountAfterExchange;
        this.exchangeStatus = exchangeStatus;
    }

    //환전 요청 상태 변경
    public void updateExchange(ExchangeStatus exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }
}

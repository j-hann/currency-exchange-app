package com.currency.exchange_app.domain.exchange.repository;

import com.currency.exchange_app.domain.exchange.entity.Exchange;
import com.currency.exchange_app.global.exception.BusinessException;
import com.currency.exchange_app.global.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    // userId로 Exchange를 조회
    List<Exchange> findByUserId(Long userId);

    default Exchange findByExchangeIdOrElseThrow(Long exchangeId){
        return findById(exchangeId).orElseThrow(()-> new BusinessException(ExceptionType.EXCHANGE_NOT_FOUND));
    }

}

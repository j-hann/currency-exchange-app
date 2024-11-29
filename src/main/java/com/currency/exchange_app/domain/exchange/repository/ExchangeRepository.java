package com.currency.exchange_app.domain.exchange.repository;

import com.currency.exchange_app.domain.exchange.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    // userId로 Exchange를 조회
    List<Exchange> findByUserId(Long userId);

    // 특정 조건 추가가 필요할 경우
    Optional<Exchange> findByUserIdAndCurrencyId(Long userId, Long currencyId);
}

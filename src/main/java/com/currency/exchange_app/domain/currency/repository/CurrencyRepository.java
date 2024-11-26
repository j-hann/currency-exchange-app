package com.currency.exchange_app.domain.currency.repository;

import com.currency.exchange_app.domain.currency.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}

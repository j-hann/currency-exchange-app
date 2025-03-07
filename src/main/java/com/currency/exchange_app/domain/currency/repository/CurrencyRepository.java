package com.currency.exchange_app.domain.currency.repository;

import com.currency.exchange_app.domain.currency.entity.Currency;
import com.currency.exchange_app.global.exception.BusinessException;
import com.currency.exchange_app.global.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    default Currency findCurrencyByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()-> new BusinessException(ExceptionType.CURRENCY_NOT_FOUND));
    };

    @Query(value = "select count (cu .id) from Currency cu where cu.exchangeRate < 0 " +
            "or cu.exchangeRate > 2000")
    Integer countBadCurrency ();

}

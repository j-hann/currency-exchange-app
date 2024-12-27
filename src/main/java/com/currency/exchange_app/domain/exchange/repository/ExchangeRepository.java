package com.currency.exchange_app.domain.exchange.repository;

import com.currency.exchange_app.domain.exchange.dto.ExchangeTotalResponseDto;
import com.currency.exchange_app.domain.exchange.entity.Exchange;
import com.currency.exchange_app.global.exception.BusinessException;
import com.currency.exchange_app.global.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    // userId로 Exchange를 조회 - 최신순 정렬
    @Query("SELECT e "
        + "FROM Exchange e "
        + "JOIN FETCH e.user "
        + "JOIN FETCH e.currency "
        + "ORDER BY e.id DESC")
    List<Exchange> findByUserId(Long userId);

    default Exchange findByExchangeIdOrElseThrow(Long exchangeId){
        return findById(exchangeId).orElseThrow(()-> new BusinessException(ExceptionType.EXCHANGE_NOT_FOUND));
    }

    @Query("SELECT new com.currency.exchange_app.domain.exchange.dto.ExchangeTotalResponseDto(ex.user.id, COUNT(*), SUM(ex.amountBeforeExchange)) "
        + "FROM Exchange ex "
        + "WHERE ex.user.id = :userId "
        + "GROUP BY ex.user.id")
    ExchangeTotalResponseDto findByIdExchangeTotalAmount(Long userId);
}

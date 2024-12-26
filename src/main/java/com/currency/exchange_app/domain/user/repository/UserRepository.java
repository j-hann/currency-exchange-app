package com.currency.exchange_app.domain.user.repository;

import com.currency.exchange_app.domain.user.entity.User;
import com.currency.exchange_app.global.exception.BusinessException;
import com.currency.exchange_app.global.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new BusinessException(ExceptionType.USER_NOT_FOUND));
    }
}

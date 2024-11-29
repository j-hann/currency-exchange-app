package com.currency.exchange_app.domain.user.dto;

import com.currency.exchange_app.domain.exchange.entity.Exchange;
import com.currency.exchange_app.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserResponseDto {
    private final Long id;//사용자 id

    private final String email;//사용자 이메일

    private final String name;//사용자 이름

    private final LocalDateTime createdAt;//생성일

    private final LocalDateTime modifiedAt;//수정일

    public UserResponseDto(Long id, String email, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}

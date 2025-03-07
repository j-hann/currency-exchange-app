package com.currency.exchange_app.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotBlank
    private final String email;//사용자 이메일

    @NotBlank
    private final String name;//사용자 이름

    public UserRequestDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}

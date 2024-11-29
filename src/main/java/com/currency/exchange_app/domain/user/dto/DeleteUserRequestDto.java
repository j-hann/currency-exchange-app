package com.currency.exchange_app.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteUserRequestDto {
    @NotBlank
    private final String email;//사용자 이메일

    public DeleteUserRequestDto(String email) {
        this.email = email;
    }
}

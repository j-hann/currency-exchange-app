package com.currency.exchange_app.domain.user.controller;

import com.currency.exchange_app.domain.user.dto.DeleteUserRequestDto;
import com.currency.exchange_app.domain.user.dto.UserRequestDto;
import com.currency.exchange_app.domain.user.dto.UserResponseDto;
import com.currency.exchange_app.domain.user.service.UserService;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;

    
    //사용자 생성
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto requestDto) throws IOException {
        UserResponseDto userResponseDto = userService.signUp(
                requestDto.getEmail(),
                requestDto.getName()
        );

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    //사용자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> userDelete(@PathVariable Long id,
                                           @Valid @RequestBody DeleteUserRequestDto requestDto) {

        userService.delete(id, requestDto.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

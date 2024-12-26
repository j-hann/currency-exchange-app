package com.currency.exchange_app.domain.user.service;

import com.currency.exchange_app.domain.user.dto.UserResponseDto;
import com.currency.exchange_app.domain.user.entity.User;
import com.currency.exchange_app.domain.user.repository.UserRepository;
import com.currency.exchange_app.global.exception.BusinessException;
import com.currency.exchange_app.global.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //사용자 생성
    public UserResponseDto signUp(String email, String name) throws IOException {
        //이메일 존재 여부 확인
        if(userRepository.existsByEmail(email)){
            throw new BusinessException(ExceptionType.EXIST_USER);
        }

        // 유저 생성
        User user = new User(email, name);

        User savedUser = userRepository.save(user);

        return UserResponseDto.toDto(savedUser);
    }

    //사용자 삭제
    public void delete(Long id, String email) {

        //사용자 id 조회
        User findUserById = userRepository.findByIdOrElseThrow(id);

        //이메일 일치 여부 확인
        if(!userRepository.existsByEmail(email)){
            throw new BusinessException(ExceptionType.EMAIL_NOT_MATCH) {
            };
        }

        //사용자 삭제
        userRepository.delete(findUserById);
    }
}

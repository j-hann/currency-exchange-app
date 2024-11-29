package com.currency.exchange_app.domain.user.entity;

import com.currency.exchange_app.domain.base.entity.BaseEntity;
import com.currency.exchange_app.domain.exchange.entity.Exchange;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

//사용자
@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//사용자 id

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Column(name = "email", unique = true)
    private String email;//사용자 email

    @Column(name = "name")
    private String name;//사용자 이름

    //양방향관계 - 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true) //영속성 전이
    private List<Exchange> ExchangeList = new ArrayList<>();

    public User() {
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
}

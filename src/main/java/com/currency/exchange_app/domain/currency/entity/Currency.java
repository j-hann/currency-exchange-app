package com.currency.exchange_app.domain.currency.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Currency {
    @Id
    private Long id;//화폐 id
}

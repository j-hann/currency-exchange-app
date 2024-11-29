package com.currency.exchange_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CurrencyExchangeAppProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeAppProjectApplication.class, args);
	}

}

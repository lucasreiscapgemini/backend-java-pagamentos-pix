package com.bradesco.pagamentospix;

import java.util.Locale;

import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.bradesco.pagamentospix.model.PagamentoPix;

@SpringBootApplication
public class PagamentosPixApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagamentosPixApplication.class, args);
	}
	
	@Bean
	public FixedLocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

}

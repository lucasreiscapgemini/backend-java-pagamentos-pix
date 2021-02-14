package com.bradesco.pagamentospix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.bradesco.pagamentospix.model.PagamentoPix;

@SpringBootApplication
public class PagamentosPixApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagamentosPixApplication.class, args);
	}

}

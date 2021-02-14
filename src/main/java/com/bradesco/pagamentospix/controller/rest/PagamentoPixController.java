package com.bradesco.pagamentospix.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.pagamentospix.model.PagamentoPix;
import com.bradesco.pagamentospix.service.PagamentoPixService;

@RestController
public class PagamentoPixController {
	
	@Autowired
	PagamentoPixService service;

	@GetMapping("/pagamentos")
	public List<PagamentoPix> listarPagamentosPorMes() {
		return null;
	}
	
	@PostMapping
	public void salvar(@RequestBody PagamentoPix pagamento) {
		service.salvar(pagamento);
	}
	
}

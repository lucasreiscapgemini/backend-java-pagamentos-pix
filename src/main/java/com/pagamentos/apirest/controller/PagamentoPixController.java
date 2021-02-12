package com.pagamentos.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagamentos.apirest.model.PagamentoPix;
import com.pagamentos.apirest.service.PagamentoPixService;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="*")
public class PagamentoPixController {
	
	@Autowired
	PagamentoPixService pagamentoPixService;
	
	@GetMapping("/pagamentos")
	public List<PagamentoPix> listaPagamentos() {
		return pagamentoPixService.listaPagamentos();
	}
	
	@GetMapping("/pagamentos/{id}")
	public PagamentoPix listaPagamento(@PathVariable(value="id") long id) {
		return pagamentoPixService.listaPagamento(id);
	}
	
	@PostMapping("/pagamento")
	public PagamentoPix salvaPagamento(@RequestBody PagamentoPix pagamento) {
		return pagamentoPixService.salvaPagamento(pagamento);
	}
	
	@DeleteMapping("/pagamento")
	public void deletaPagamento(@RequestBody PagamentoPix pagamento) {
		pagamentoPixService.deletaPagamento(pagamento);
	}
	
	@PutMapping("/pagamento")
	public PagamentoPix atualizaPagamento(@RequestBody PagamentoPix pagamento) {
		return pagamentoPixService.atualizaPagamento(pagamento);
	}

}

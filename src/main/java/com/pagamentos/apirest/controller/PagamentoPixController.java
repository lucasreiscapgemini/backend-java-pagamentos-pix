package com.pagamentos.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagamentos.apirest.models.PagamentoPix;
import com.pagamentos.apirest.service.PagamentoPixService;

@RestController
@RequestMapping(value="/api")
public class PagamentoPixController {
	
	@Autowired
	PagamentoPixService pagamentoPixService;
	
	@GetMapping("/pagamentos")
	public ResponseEntity<List<PagamentoPix>> listaPagamentos() {
		return ResponseEntity.ok(pagamentoPixService.listaPagamentos());
	}
	
	@GetMapping("/pagamentos/{id}")
	public ResponseEntity<PagamentoPix> listaPagamento(@PathVariable(value="id") long id) {
		return ResponseEntity.ok(pagamentoPixService.listaPagamento(id));
	}
	
	@PostMapping("/pagamentos")
	public ResponseEntity<PagamentoPix> salvaPagamento(@RequestBody PagamentoPix pagamento) {
		return ResponseEntity.ok(pagamentoPixService.salvaPagamento(pagamento));
	}
	
	@DeleteMapping("/pagamentos")
	public ResponseEntity deletaPagamento(@RequestBody PagamentoPix pagamento) {
		try {
			pagamentoPixService.deletaPagamento(pagamento);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/pagamentos/{id}")
	public ResponseEntity deletaPagamentoById(@PathVariable(value="id") Long id) {
		try {
			pagamentoPixService.deletaPagamento(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/pagamentos")
	public ResponseEntity<PagamentoPix> atualizaPagamento(@RequestBody PagamentoPix pagamento) {
		return ResponseEntity.ok(pagamentoPixService.atualizaPagamento(pagamento));
	}
	
}

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

import com.pagamentos.apirest.exception.PagamentoPixException;
import com.pagamentos.apirest.models.PagamentoPix;
import com.pagamentos.apirest.models.dto.PagamentoPixDTO;
import com.pagamentos.apirest.service.PagamentoPixService;

@RestController
@RequestMapping(value = "/api")
public class PagamentoPixController {

	@Autowired
	PagamentoPixService pagamentoPixService;

	@GetMapping("/pagamentos")
	public ResponseEntity<List<PagamentoPix>> listaPagamentos() {
		return ResponseEntity.ok(pagamentoPixService.listaPagamentos());
	}

	@GetMapping("/pagamentos/{id}")
	public ResponseEntity<PagamentoPixDTO> consultaPagamento(@PathVariable(value = "id") long id) {
		try {
			return ResponseEntity.ok(pagamentoPixService.exibePagamento(id));
		} catch (PagamentoPixException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/pagamentos")
	public ResponseEntity<PagamentoPixDTO> salvaPagamento(@RequestBody PagamentoPix pagamento) {
		try {
			return ResponseEntity.ok(pagamentoPixService.salvaPagamento(pagamento));
		} catch (PagamentoPixException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/pagamentos")
	public ResponseEntity deletaPagamento(@RequestBody PagamentoPix pagamento) {
		try {
			pagamentoPixService.deletaPagamento(pagamento);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/pagamentos/{id}")
	public ResponseEntity deletaPagamentoById(@PathVariable(value = "id") Long id) {
		try {
			pagamentoPixService.deletaPagamento(id);
			return ResponseEntity.ok().build();
		} catch (PagamentoPixException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/pagamentos")
	public ResponseEntity<PagamentoPixDTO> atualizaPagamento(@RequestBody PagamentoPix pagamento) {
		try {
			return ResponseEntity.ok(pagamentoPixService.atualizaPagamento(pagamento));
		} catch (PagamentoPixException e) {
			return ResponseEntity.notFound().build();
		}
	}

}

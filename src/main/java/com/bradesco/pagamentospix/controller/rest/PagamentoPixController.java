package com.bradesco.pagamentospix.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.pagamentospix.model.PagamentoPix;
import com.bradesco.pagamentospix.service.PagamentoPixService;

@RestController
public class PagamentoPixController {
	
	@Autowired
	PagamentoPixService service;

	// Lista pagamentos do mês atual
	@GetMapping("/pagamentos")
	public List<PagamentoPix> listarPagamentosPorMes() {
		return service.listarPagamentosPorMes();
	}
	
	// Persiste pagamento e atualiza percentuais de cada pagamento com o mês correspondente
	@PostMapping
	public void salvar(@RequestBody PagamentoPix pagamento) {
		service.salvar(pagamento);
		service.atualizarPercentuaisPorMes();

	}
	
	// Remove pagamento cujo id é passado na URL
	@DeleteMapping("{id}")
	public void remover(@PathVariable Long id) {
		service.remover(id);
	}
}

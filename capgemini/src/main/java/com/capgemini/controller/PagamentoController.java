package com.capgemini.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.entity.PagamentoEntity;
import com.capgemini.model.service.PagamentoService;
import com.capgemini.utils.ResponseService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService service;

	@PostMapping
	@Transactional
	public ResponseEntity<Object> post(@RequestBody @Valid PagamentoEntity entity) {
		ResponseService responseService = this.service.post(entity);
		return ResponseEntity.status(responseService.getStatus()).body(responseService.getResponse());
	}

	@GetMapping
	@Transactional
	public ResponseEntity<Object> getAll() {
		ResponseService responseService = this.service.getAll();
		return ResponseEntity.status(responseService.getStatus()).body(responseService.getResponse());
	}

}
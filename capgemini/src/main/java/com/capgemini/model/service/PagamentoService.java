package com.capgemini.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.capgemini.model.entity.PagamentoEntity;
import com.capgemini.model.repository.PagamentoRepository;
import com.capgemini.utils.ResponseService;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;

	public ResponseService post(PagamentoEntity entity) {
		ResponseService responseService = new ResponseService();

		PagamentoEntity newEntity = this.repository.save(entity);

		responseService.setStatus(HttpStatus.CREATED);

		if (newEntity != null) {
			responseService.setData(newEntity);
		}

		return responseService;
	}

	public ResponseService getAll() {
		ResponseService responseService = new ResponseService();

		List<PagamentoEntity> companies = this.repository.findAll();

		if (!companies.isEmpty()) {
			responseService.setData(companies);
		}

		return responseService;
	}

}
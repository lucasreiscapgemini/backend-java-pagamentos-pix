package com.capgemini.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.utils.ResponseService;

@RestController
@RequestMapping("/")
public class IndexController {
	@Autowired
	private MessageSource messageSource;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> welcomeDentesys() {
		ResponseService responseService = new ResponseService(
				this.messageSource.getMessage("welcome", null, Locale.getDefault()));

		return ResponseEntity.status(responseService.getStatus()).body(responseService.getResponse());
	}

}
package com.capgemini.exception;

import com.capgemini.utils.ResponseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CapgeminiException {
    public static void handleBusinessException(String field, String message) {
		try {
			var alert = new ResponseService.ResponseServiceField(field, message);
			throw new BusinessException(new ObjectMapper().writeValueAsString(alert));
		} catch (JsonProcessingException e) {
		}
	}

	public static void handleNotFoundException() {
		throw new NotFoundException();
	}

	public static void handleInternalException() {
		throw new InternalException();
	}

	public static void handleInternalException(String message) {
		throw new InternalException(message);
	}
}

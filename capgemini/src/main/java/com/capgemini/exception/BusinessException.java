package com.capgemini.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected BusinessException(String message) {
		super(message);
	};

}
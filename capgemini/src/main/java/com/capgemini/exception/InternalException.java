package com.capgemini.exception;

public class InternalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected InternalException() {
		super();
	}

	protected InternalException(String message) {
		super(message);
	}
}

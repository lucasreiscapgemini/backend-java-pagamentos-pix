package com.capgemini.exception;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.capgemini.utils.ResponseService;
import com.capgemini.utils.ResponseService.ResponseServiceField;

@ControllerAdvice
public class CapgeminiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(InternalException.class)
	public ResponseEntity<Object> handleInternalException(Exception ex, WebRequest request) {
		// Depois criar estratégia de log de erros.
		System.out.println(ex.getMessage());
		String message = this.messageSource.getMessage("error.request", null, Locale.getDefault());
		ResponseService responseService = new ResponseService(message, HttpStatus.INTERNAL_SERVER_ERROR);

		return super.handleExceptionInternal(ex, responseService.getResponse(), new HttpHeaders(),
				responseService.getStatus(), request);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
		String message = this.messageSource.getMessage("notfound.request", null, Locale.getDefault());
		ResponseService responseService = new ResponseService(message, HttpStatus.NOT_FOUND);

		return super.handleExceptionInternal(ex, responseService.getResponse(), new HttpHeaders(),
				responseService.getStatus(), request);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<ResponseServiceField> typeRef = new TypeReference<ResponseServiceField>() {
		};
		String message = this.messageSource.getMessage("bad.request", null, Locale.ENGLISH);
		ResponseService responseService = new ResponseService(message, HttpStatus.BAD_REQUEST);

		try {
			ResponseServiceField alert = mapper.readValue(ex.getMessage(), typeRef);

			if (alert != null) {
				responseService.addAlert(alert);
			}
		} catch (JsonProcessingException e) {
		}

		return super.handleExceptionInternal(ex, responseService.getResponse(), new HttpHeaders(),
				responseService.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = this.messageSource.getMessage("bad.request", null, Locale.getDefault());
		ResponseService responseService = new ResponseService(message, status); // HttpStatus.BAD_REQUEST
		return super.handleExceptionInternal(ex, responseService.getResponse(), headers, responseService.getStatus(),
				request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = this.messageSource.getMessage("bad.request", null, Locale.getDefault());
		ResponseService responseService = new ResponseService(message, status);

		for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
			var objectField = ((FieldError) objectError);

			String field = (objectField instanceof FieldError) ? objectField.getField() : objectField.getObjectName();
			String messageField = this.messageSource.getMessage(objectError, Locale.getDefault());

			responseService.addAlert(new ResponseService.ResponseServiceField(field, messageField));
		}

		Collections.sort(responseService.getAlerts(), new Comparator<ResponseServiceField>() {
			@Override
			public int compare(ResponseServiceField o1, ResponseServiceField o2) {
				return o1.getField().compareTo(o2.getField());
			}
		});

		return super.handleExceptionInternal(ex, responseService.getResponse(), headers, responseService.getStatus(),
				request);
	}

}
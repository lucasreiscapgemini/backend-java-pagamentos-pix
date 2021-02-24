package com.capgemini.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ResponseService {

	private Object data;
	private HttpStatus status = HttpStatus.OK;
	private ArrayList<ResponseServiceField> alerts = new ArrayList<ResponseServiceField>();

	public ResponseService() {
	}

	public ResponseService(Object data) {
		if (data != null) {
			this.data = data;
		}
	}

	public ResponseService(Object data, HttpStatus status) {
		if (data != null) {
			this.data = data;
		}

		if (status != null) {
			this.status = status;
		}
	}

	public ResponseService(Object data, HttpStatus status, ArrayList<ResponseServiceField> alerts) {
		if (data != null) {
			this.data = data;
		}

		if (status != null) {
			this.status = status;
		}

		if (alerts != null) {
			this.alerts = alerts;
		}
	}

	public static class ResponseServiceField {
		private String field;
		private ArrayList<Object> messages = new ArrayList<>();

		public ResponseServiceField() {
			super();
		}

		public ResponseServiceField(String field) {
			super();
			this.field = field;
		}

		public ResponseServiceField(String field, Object message) {
			super();
			this.field = field;

			if (message != null) {
				this.messages.add(message);
			}
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public ArrayList<Object> getMessages() {
			return messages;
		}

		public void addMensagem(Object message) {
			if (message != null) {
				this.messages.add(message);
			}
		}

	}

	public void addAlert(ResponseServiceField responseServiceField) {
		Boolean existeField = false;

		for (ResponseServiceField rsf : this.alerts) {
			if (rsf.getField().equals(responseServiceField.getField())) {
				rsf.getMessages().add(responseServiceField.getMessages().get(0));
				existeField = true;
				break;
			}
		}

		if (!existeField) {
			this.alerts.add(responseServiceField);
		}
	}

	public Map<String, Object> getResponse() {
		LinkedHashMap<String, Object> responseDados = new LinkedHashMap<>();
		responseDados.put("data", (this.data != null) ? this.data : new ArrayList<>());

		if (this.alerts != null && !this.alerts.isEmpty()) {
			responseDados.put("alerts", this.alerts);
		}

		return responseDados;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		if (data != null) {
			this.data = data;
		}
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		if (status != null) {
			this.status = status;
		}
	}

	public ArrayList<ResponseServiceField> getAlerts() {
		return alerts;
	}

	public void setAlerts(ArrayList<ResponseServiceField> alerts) {
		if (alerts != null) {
			this.alerts = alerts;
		}
	}

}
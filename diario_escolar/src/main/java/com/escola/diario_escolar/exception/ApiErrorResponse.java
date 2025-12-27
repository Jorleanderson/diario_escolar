package com.escola.diario_escolar.exception;

import java.util.Map;

public class ApiErrorResponse {

    private int status;
    private String error;
    private Map<String, String> fields;
    
    public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Map<String, String> getFields() {
		return fields;
	}
	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}
	

}

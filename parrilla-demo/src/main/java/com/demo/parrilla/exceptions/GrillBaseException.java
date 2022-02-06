package com.demo.parrilla.exceptions;

public class GrillBaseException extends Exception {

	private static final long serialVersionUID = 4313900055378291321L;
	
	public GrillBaseException(String message) {
		super(message);
	}

	public String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}

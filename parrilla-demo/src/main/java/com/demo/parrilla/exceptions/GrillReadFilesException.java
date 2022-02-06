package com.demo.parrilla.exceptions;

public class GrillReadFilesException extends GrillBaseException {

	private static final long serialVersionUID = 4620543925668860586L;

	public static String defaultErrorCode = "ERR_READ_DATA";

	public GrillReadFilesException(String code, String message) {
		super(message);
		this.code = code;
	}

	public GrillReadFilesException(String message) {
		super(message);
		this.code = defaultErrorCode;
	}

}

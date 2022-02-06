package com.demo.parrilla.exceptions;

public class GrillFilesException extends GrillBaseException {

	private static final long serialVersionUID = -3100962246543746840L;

	public static String defaultErrorCode = "ERR_FILE";

	public GrillFilesException(String code, String message) {
		super(message);
		this.code = code;
	}

	public GrillFilesException(String message) {
		super(message);
		this.code = defaultErrorCode;
	}

}

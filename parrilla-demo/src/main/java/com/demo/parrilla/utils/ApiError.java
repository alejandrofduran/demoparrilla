package com.demo.parrilla.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import springfox.documentation.annotations.ApiIgnore;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "detail"})
@ApiIgnore
public class ApiError {

	@JsonProperty("code")
	public String code;
	
	@JsonProperty("detail")
	public String detail;

    public ApiError() {
		super();
	}

	public ApiError(String code, String detail){
    	this.code = code;
    	this.detail = detail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "ApiError{" +
				"code='" + code + '\'' +
				", detail='" + detail + '\'' +
				'}';
	}
}
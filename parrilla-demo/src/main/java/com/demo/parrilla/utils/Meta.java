package com.demo.parrilla.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import springfox.documentation.annotations.ApiIgnore;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"method", "operation"})
@ApiIgnore
public class Meta {

	@JsonProperty("method")
	public Object method;
	
	@JsonProperty("operation")
	public String operation;
	
	public Object getMethod() {
		return method;
	}

	public void setMethod(Object method) {
		this.method = method;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	
	@Override
	public String toString() {
		return "Meta{" +
				"method=" + method +
				", operation='" + operation + '\'' +
				'}';
	}
}

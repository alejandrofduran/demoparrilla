package com.demo.parrilla.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import springfox.documentation.annotations.ApiIgnore;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiIgnore
public class StandardRestResponse<T, S> {

	@JsonProperty("meta")
	private Meta meta;

	@JsonProperty("data")
	private List<T> data;

	@JsonProperty("errors")
	private List<S> errors;

	public StandardRestResponse() {
		super();
	}

	public StandardRestResponse(Meta meta, List<T> data, List<S> errors) {
		this.meta = meta;
		this.data = data;
		this.errors = errors;
	}
	
	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	public List<S> getErrors() {
		return errors;
	}

	public void setErrors(List<S> errors) {
		this.errors = errors;
	}

	// Static builder methods
	public static ResponseBuilder get() {
		return method(HttpMethod.GET);
	}

	public static ResponseBuilder post() {
		return method(HttpMethod.POST);
	}

	public static ResponseBuilder delete() {
		return method(HttpMethod.DELETE);
	}

	public static ResponseBuilder put() {
		return method(HttpMethod.PUT);
	}

	public static ResponseBuilder method(HttpMethod method) {
		return new DefaultBuilder(method);
	}

	public static ResponseBuilder method(String method) {
		return new DefaultBuilder(method);
	}

	/**
	 * Define un builder para crear un mensaje de respuesta standard
	 */
	public interface ResponseBuilder {

		ResponseBuilder method(String method);

		ResponseBuilder operation(String operation);

		<T, S> StandardRestResponse<T, S> build(List<T> data, List<S> errors);

		<T, S> StandardRestResponse<T, S> data();
		
		<T, S> StandardRestResponse<T, S> data(List<T> data);

		<T, S> StandardRestResponse<T, S> errors(List<S> errors);

	}

	private static class DefaultBuilder implements ResponseBuilder {

		private final Meta meta = new Meta();

		public DefaultBuilder(Object method) {
			this.meta.setMethod(method);
		}

		@Override
		public ResponseBuilder method(String method) {
			this.meta.setMethod(method);
			return this;
		}

		@Override
		public ResponseBuilder operation(String operation) {
			this.meta.setOperation(operation);
			return this;
		}

		@Override
		public <T, S> StandardRestResponse<T, S> build(List<T> data, List<S> errors) {
			return new StandardRestResponse<>(this.meta, data, errors);
		}
		
		@Override
		public <T, S> StandardRestResponse<T, S> data() {
			return new StandardRestResponse<>(this.meta, new ArrayList<T>(), null);
		}
		
		@Override
		public <T, S> StandardRestResponse<T, S> data(List<T> data) {
			return new StandardRestResponse<>(this.meta, data, null);
		}

		@Override
		public <T, S> StandardRestResponse<T, S> errors(List<S> errors) {
			return new StandardRestResponse<>(this.meta, null, errors);
		}
	}

	@Override
	public String toString() {
		return "StandardRestResponse{" +
				"meta=" + meta +
				", data=" + data +
				", errors=" + errors +
				'}';
	}
}

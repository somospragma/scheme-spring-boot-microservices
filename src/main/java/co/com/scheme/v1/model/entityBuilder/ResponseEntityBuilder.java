package co.com.scheme.v1.model.entityBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.scheme.v1.domain.response.Response;

public class ResponseEntityBuilder {
	public static ResponseEntity<Object> build(Response<Object> err) {
		return new ResponseEntity<Object>(err, HttpStatus.valueOf(err.getStatus()));
	}
}

package co.com.scheme.v1.util;

import org.springframework.http.HttpStatus;

import co.com.scheme.v1.domain.response.Response;

public class Util {
	private Util() {
	}

	public static <T> Response<T> callResponse(String msg, Object payload) {
        Response<T> response = new Response<T>();
		response.setData((T) payload);
		response.setMessage(msg);
		response.setStatus(HttpStatus.OK.value());
		return response;
	}
}

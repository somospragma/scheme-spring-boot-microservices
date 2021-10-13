package co.com.scheme.v1.domain.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
	
    private String systemData;
	
	private Integer status;
	
	private Integer errorCode;
	
	private String message;
	
	private List<String> errors;

	private T data;
	
	public Response() {
		Date date = new Date();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		this.setSystemData(fmt.format(date));
	}

	public String getSystemData() {
		return systemData;
	}

	public void setSystemData(String systemData) {
		this.systemData = systemData;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "Response [systemData=" + systemData + ", status=" + status + ", errorCode=" + errorCode + ", message="
				+ message + ", errors=" + errors + ", data=" + data + "]";
	}

}
	

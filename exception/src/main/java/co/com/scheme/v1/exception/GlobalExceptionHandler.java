package co.com.scheme.v1.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.com.scheme.v1.domain.response.Response;
import co.com.scheme.v1.model.entityBuilder.*;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();

		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

		details.add(builder.toString());

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Bad Request");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);

	}

	// handleHttpMessageNotReadable : triggers when the JSON is malformed
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add("Required request body is missing.");

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Malformed JSON request");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	// handleMethodArgumentNotValid : triggers when @Valid fails
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> /* error.getObjectName()+ " " + */ error.getField() + ": " + error.getDefaultMessage()
						+ " [" + error.getRejectedValue() + "]")
				.collect(Collectors.toList());

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Validation Errors");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	// handleMissingServletRequestParameter : triggers when there are missing
	// parameters
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getParameterName() + " parameter is missing");

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Missing Parameters");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	// handleMethodArgumentTypeMismatch : triggers when a parameter's type does not
	// match
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Mismatch Type");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	// handleConstraintViolationException : triggers when @Validated fails
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(Exception ex, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Constraint Violation");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	// handleNoHandlerFoundException : triggers when the handler method is invalid
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Method Not Found");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);

	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add("Request method [" + ex.getMethod() + "] not supported.");

		Response<Object> err = new Response<Object>();
		err.setErrorCode(HttpStatus.BAD_REQUEST.value());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Method Not Supported");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Media Type Not Acceptable");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getVariableName());

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Missing Path Variable");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Request Binding Exception");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getPropertyName() + " : " + ex.getMessage() + ": value [" + ex.getValue() + "].");

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Type Mismatch");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		Response<Object> err = new Response<Object>();
		err.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setMessage("Error occurred");
		err.setErrors(details);
		return ResponseEntityBuilder.build(err);

	}

	@ExceptionHandler({ NullPointerException.class })
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		Response<Object> err = new Response<Object>();
		err.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setMessage("NullPointerException");
		err.setErrors(details);
		return ResponseEntityBuilder.build(err);

	}

	
	// handleResourceNotFoundException : triggers when there is not resource with
	// the specified ID in BDD
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setErrorCode(HttpStatus.NOT_FOUND.value());
		err.setMessage("Resource Not Found");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}
	
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setErrorCode(HttpStatus.BAD_REQUEST.value());
		err.setMessage("JsonProcessingException Error");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		Response<Object> err = new Response<Object>();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setErrorCode(HttpStatus.BAD_REQUEST.value());
		err.setMessage("JsonProcessingException Error");
		err.setErrors(details);

		return ResponseEntityBuilder.build(err);
	}
	
	
}

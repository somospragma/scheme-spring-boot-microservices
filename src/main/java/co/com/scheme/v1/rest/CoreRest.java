package co.com.scheme.v1.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.scheme.v1.business.PersonBusiness;
import co.com.scheme.v1.domain.interfaces.Names;
import co.com.scheme.v1.domain.request.PersonRequestDTO;
import co.com.scheme.v1.domain.request.SearchBySecretRequestDTO;
import co.com.scheme.v1.domain.response.Response;
import co.com.scheme.v1.model.entities.Person;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.MediaType;

import static co.com.scheme.v1.util.Messages.CREATE;
import static co.com.scheme.v1.util.Messages.GET_DATA_OK;

import java.util.List;

@RestController
@RequestMapping("/api/1/")
public class CoreRest {

	@Autowired
	PersonBusiness personBusiness;
	
	@ApiOperation(value = "Permite crear una persona", notes = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = CREATE, response = Response.class),
			@ApiResponse(code = 400, message = "Error", response = Response.class),
			@ApiResponse(code = 500, message = "Error", response = Response.class) })
	@PostMapping(value = "/create-person", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Person> registerPerson(@RequestBody @Valid PersonRequestDTO req) throws Exception{
		return personBusiness.registerPerson(req);
	}
	
	@ApiOperation(value = "Permite consultar un registro", notes = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = GET_DATA_OK, response = Response.class),
			@ApiResponse(code = 400, message = "Error", response = Response.class),
			@ApiResponse(code = 500, message = "Error", response = Response.class) })
	@PostMapping(value = "/search-person", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Person> searchPerson(@RequestBody @Valid SearchBySecretRequestDTO req) throws Exception {
		return personBusiness.searchPerson(req);
	}

}

package co.com.scheme.v1.rest;

import co.com.scheme.v1.business.PersonBusiness;
import co.com.scheme.v1.domain.interfaces.Names;
import co.com.scheme.v1.domain.response.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.com.scheme.v1.util.Messages.GET_DATA_OK;

@RestController
@RequestMapping("/api/2/")
public class CoreRestTwo {
    @Autowired
    PersonBusiness personBusiness;

    @ApiOperation(value = "Permite consultar todos los nombres", notes = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 200, message = GET_DATA_OK, response = Response.class),
            @ApiResponse(code = 400, message = "Error", response = Response.class),
            @ApiResponse(code = 500, message = "Error", response = Response.class) })
    @GetMapping(value = "/get-all-person", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<Names>> getAllperson() throws Exception {
        return personBusiness.getAllperson();
    }
}

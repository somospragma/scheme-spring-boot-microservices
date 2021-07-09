package co.com.scheme.v1.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.scheme.v1.domain.interfaces.Names;
import co.com.scheme.v1.domain.request.PersonRequestDTO;
import co.com.scheme.v1.domain.request.SearchBySecretRequestDTO;
import co.com.scheme.v1.domain.response.Response;
import co.com.scheme.v1.exception.ResourceNotFoundException;
import co.com.scheme.v1.model.entities.Person;
import co.com.scheme.v1.repository.PersonRepository;
import static co.com.scheme.v1.util.Messages.CREATE;
import static co.com.scheme.v1.util.Messages.NOT_FOUND;
import static co.com.scheme.v1.util.Messages.GET_DATA_OK;

import java.util.List;
import java.util.Optional;

import co.com.scheme.v1.util.Util;

@Service
public class PersonBusinessImpl implements PersonBusiness  {

	@Autowired
	PersonRepository personRepository;
	
	@Override
	public Response<Person> registerPerson(PersonRequestDTO req) throws Exception {
		Person data = new Person();
		data.setLastNames(req.getLastNames());
		data.setNames(req.getNames());
		
		Person body = personRepository.save(data);
		
		return Util.callResponse(CREATE, body);
	}

	@Override
	public Response<Person> searchPerson(SearchBySecretRequestDTO req) throws Exception {
		Optional<Person> body = personRepository.findBySecret(req.getSecret());
		
		if(body.isEmpty()) {
			throw new ResourceNotFoundException(NOT_FOUND);
		}
		
		return Util.callResponse(GET_DATA_OK, body.get());
	}

	@Override
	public Response<List<Names>> getAllperson() throws Exception {
		List<Names> body = personRepository.getAllNames();
		if(body.isEmpty()) {
			throw new ResourceNotFoundException(NOT_FOUND);
		}
		
		return Util.callResponse(GET_DATA_OK, body);
	}
	
	

}

package co.com.scheme.v1.business;

import java.util.List;

import co.com.scheme.v1.domain.interfaces.Names;
import co.com.scheme.v1.domain.request.PersonRequestDTO;
import co.com.scheme.v1.domain.request.SearchBySecretRequestDTO;
import co.com.scheme.v1.domain.response.Response;
import co.com.scheme.v1.model.entities.Person;

public interface PersonBusiness {
  Response<Person> registerPerson(PersonRequestDTO req) throws Exception;
  Response<Person> searchPerson(SearchBySecretRequestDTO req) throws Exception;
  Response<List<Names>> getAllperson() throws Exception;
}

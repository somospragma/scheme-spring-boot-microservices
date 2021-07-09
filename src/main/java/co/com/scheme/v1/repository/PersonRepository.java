package co.com.scheme.v1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.scheme.v1.domain.interfaces.Names;
import co.com.scheme.v1.model.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	public Optional<Person> findBySecret(String secret);
	
	@Query(nativeQuery=true, value="select p.names, p.secret from persons p ")
	public List<Names> getAllNames(); 
}

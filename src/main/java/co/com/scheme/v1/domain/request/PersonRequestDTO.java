package co.com.scheme.v1.domain.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class PersonRequestDTO {

	@Pattern(regexp = "^[ a-zA-Z0-9áéíóúÁÉÍÓÚäëïöüÄËÏÖÜñÑ_-]+$", message= "names is alphanumeric.")
	@Length(min=3,max=50, message="names min 3, max 50 characters.")
	@NotNull(message="names is required.")
	private String names;
	
	@Pattern(regexp = "^[ a-zA-Z0-9áéíóúÁÉÍÓÚäëïöüÄËÏÖÜñÑ_-]+$", message= "LastaNames is alphanumeric.")
	@Length(min=3,max=50, message="Lastanames min 3, max 50 characters.")
	@NotNull(message="lastNames is required.")
	private String lastNames;
	
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
    public String getLastNames() {
		return lastNames;
	}
	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}
	@Override
	public String toString() {
		return "PersonDTO [names=" + names + ", LastName=" + lastNames + "]";
	}
}

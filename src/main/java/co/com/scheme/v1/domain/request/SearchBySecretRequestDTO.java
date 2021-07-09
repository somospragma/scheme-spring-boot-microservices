package co.com.scheme.v1.domain.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class SearchBySecretRequestDTO {
	@Pattern(regexp = "^[a-zA-Z0-9_-]+$", message= "secret is alphanumeric.")
	@NotNull(message="secret is required.")
	private String secret;

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public String toString() {
		return "SearchBySecretRequestDTO [secret=" + secret + "]";
	}
}

package rs.ac.uns.ftn.xml.team17.authservice.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {
	@NotBlank(message = "Please, enter your username.")
	private String username;

	@NotBlank(message = "Please, enter your password.")
	private String password;
}

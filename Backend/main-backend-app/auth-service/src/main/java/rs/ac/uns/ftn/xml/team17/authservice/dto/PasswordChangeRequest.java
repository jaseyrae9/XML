package rs.ac.uns.ftn.xml.team17.authservice.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PasswordChangeRequest {
	@NotBlank(message = "Please, enter your old password.")
	private String oldPassword;
	
	@NotBlank(message = "Please, enter your new password.")
	private String newPassword;
}

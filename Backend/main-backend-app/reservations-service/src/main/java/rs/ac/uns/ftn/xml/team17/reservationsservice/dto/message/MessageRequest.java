package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MessageRequest {
	@NotBlank (message = "Please, enter your message.")
	private String message;
}

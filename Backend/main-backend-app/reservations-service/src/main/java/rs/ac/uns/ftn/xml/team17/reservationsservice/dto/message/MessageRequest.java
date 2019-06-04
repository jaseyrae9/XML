package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MessageRequest {
	@NotNull(message = "Please, choose reservation.")
	private Integer reservationId;
	@NotBlank (message = "Please, enter your message.")
	private String message;
}

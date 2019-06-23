package rs.ac.uns.ftn.xml.team17.authservice.dto.customer;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerStatusChangeDTO {
	@NotNull(message = "Please, choose status.")
	private Boolean blocked;
}

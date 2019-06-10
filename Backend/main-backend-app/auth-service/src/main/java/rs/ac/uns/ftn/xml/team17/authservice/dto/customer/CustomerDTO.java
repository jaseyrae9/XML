package rs.ac.uns.ftn.xml.team17.authservice.dto.customer;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.User;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {
	private Integer id;
	private String username;
	@NotNull(message = "Blocked is required.")
	private Boolean blocked;

	public CustomerDTO(User customer) {
		this.id = customer.getId();
		this.username = customer.getUsername();
		this.blocked = customer.getBlocked();
	}
}

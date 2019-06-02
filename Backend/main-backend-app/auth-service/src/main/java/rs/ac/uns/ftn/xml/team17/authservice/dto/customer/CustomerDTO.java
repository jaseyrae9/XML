package rs.ac.uns.ftn.xml.team17.authservice.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.User;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {

	private String username;
	private Boolean blocked;

	public CustomerDTO(User customer) {
		this.username = customer.getUsername();
		this.blocked = customer.getBlocked();
	}
}
